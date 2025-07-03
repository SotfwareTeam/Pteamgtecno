<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

// Composer
require 'vendor/autoload.php';

ini_set('display_errors', 1);
error_reporting(E_ALL);

// CORS
header('Access-Control-Allow-Origin: http://localhost:5173');
header('Access-Control-Allow-Methods: POST, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');
header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

// DB config
$host = 'localhost';
$db   = 'dance';
$user = 'root';
$pass = '';
$charset = 'utf8mb4';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$db;charset=$charset", $user, $pass);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Leer JSON del frontend
    $input = json_decode(file_get_contents('php://input'), true);
    $id_clase = $input['id_clase'] ?? null;
    $mensaje = $input['mensaje'] ?? '';
    $enviar_al_maestro = $input['enviar_al_maestro'] ?? false;

    if (!$id_clase || !$mensaje) {
        http_response_code(400);
        echo json_encode(['error' => 'Faltan datos necesarios.']);
        exit;
    }

    // Buscar correos de estudiantes
    $stmt = $pdo->prepare("
        SELECT u.correo
        FROM clases_usuarios cu
        JOIN usuarios u ON cu.id_usuario = u.id_usuario
        WHERE cu.id_clase = ?
    ");
    $stmt->execute([$id_clase]);
    $correos_estudiantes = $stmt->fetchAll(PDO::FETCH_COLUMN);

    $correos_destino = $correos_estudiantes;

    // Incluir profesor si aplica
    if ($enviar_al_maestro) {
        $stmt = $pdo->prepare("
            SELECT u.correo
            FROM clases c
            JOIN usuarios u ON c.profesor = u.id_usuario
            WHERE c.id_clase = ? AND u.correo IS NOT NULL
        ");
        $stmt->execute([$id_clase]);
        $correo_maestro = $stmt->fetchColumn();
        if ($correo_maestro) {
            $correos_destino[] = $correo_maestro;
        }
    }

    // Enviar correos
    $errores = [];
    foreach ($correos_destino as $correo) {
        $mail = new PHPMailer(true);

        try {
            // SMTP config
            $mail->isSMTP();
            $mail->Host = 'smtp.gmail.com';
            $mail->SMTPAuth = true;
            $mail->Username = 'sotfwareteam@gmail.com'; // tu correo Gmail
            $mail->Password = 'utvpbtmvdtuyaech';          // tu contraseña de aplicación
            $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
            $mail->Port = 587;

            $mail->setFrom('softwareteam@gmail.com', 'Academia de Baile');
            $mail->addAddress($correo);

            $mail->isHTML(true);
            $mail->CharSet = 'UTF-8';
            $mail->Subject = '📢 Notificación de clase';

            // HTML amigable
            $mail->Body = "
                <html>
                <head>
                  <meta charset='UTF-8'>
                  <style>
                    body { font-family: Arial, sans-serif; background: #f4f4f4; color: #333; }
                    .container { background: white; padding: 20px; border-radius: 8px; max-width: 600px; margin: auto; }
                    .header { background: #9854b1; color: white; padding: 10px; text-align: center; font-size: 18px; border-radius: 6px 6px 0 0; }
                    .content { padding: 10px 0; }
                    .footer { margin-top: 20px; font-size: 12px; text-align: center; color: #aaa; }
                  </style>
                </head>
                <body>
                  <div class='container'>
                    <div class='header'>📅 Notificación de Clase</div>
                    <div class='content'>
                      <p><strong>Hola,</strong></p>
                      <p>{$mensaje}</p>
                      <p><strong>Fecha:</strong> " . date('d/m/Y') . "</p>
                    </div>
                    <div class='footer'>Academia de Baile - Gracias por tu participación 💃🕺</div>
                  </div>
                </body>
                </html>
            ";

            $mail->AltBody = $mensaje;

            $mail->send();
        } catch (Exception $e) {
            $errores[] = "Error a $correo: " . $mail->ErrorInfo;
        }
    }

    if ($errores) {
        echo json_encode(['success' => false, 'error' => implode(', ', $errores)]);
    } else {
        echo json_encode(['success' => true, 'correos_enviados' => $correos_destino]);
    }
} catch (Exception $e) {
    http_response_code(500);
    echo json_encode(['error' => 'Error interno: ' . $e->getMessage()]);
}
