<?php
require 'vendor/phpmailer/phpmailer/src/PHPMailer.php';
require 'vendor/phpmailer/phpmailer/src/Exception.php';
require 'vendor/phpmailer/phpmailer/src/SMTP.php';

require 'vendor/autoload.php'; // Usamos Composer y PHPMailer
// --- Agregar estas cabeceras al principio ---
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
// --- Continúa tu código ---

$conexion = new mysqli('localhost', 'root', '', 'dance');

if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    http_response_code(200);
    exit;
}
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;
if ($conexion->connect_error) {
    die(json_encode(['error' => 'Error de conexión']));
}

$data = json_decode(file_get_contents("php://input"), true);

$email = $data['email'] ?? '';

if (empty($email)) {
    echo json_encode(['error' => 'Email requerido']);
    exit;
}

// Verificar si el correo existe en la BD
$stmt = $conexion->prepare("SELECT * FROM usuarios WHERE correo = ?");
$stmt->bind_param("s", $email);
$stmt->execute();
$result = $stmt->get_result();

if (!$user = $result->fetch_assoc()) {
    echo json_encode(['error' => 'Correo no encontrado']);
    exit;
}

// Crear token único
$token = bin2hex(random_bytes(50));

// Guardarlo en una tabla de reset (deberías crear una tabla `password_resets`)
$stmtInsert = $conexion->prepare("INSERT INTO password_resets (email, token) VALUES (?, ?)");
$stmtInsert->bind_param("ss", $email, $token);
$stmtInsert->execute();

// Enviar correo
$mail = new PHPMailer(true);

try {
    $mail->isSMTP();
    $mail->Host = 'smtp.gmail.com';  // Servidor SMTP de Gmail
    $mail->SMTPAuth = true;
    $mail->Username = 'TU_CORREO@gmail.com'; // Tu Gmail
    $mail->Password = 'TU_CONTRASEÑA_APP';   // Tu contraseña de aplicación Gmail
    $mail->SMTPSecure = 'tls';
    $mail->Port = 587;

    $mail->setFrom('TU_CORREO@gmail.com', 'Academia de Baile');
    $mail->addAddress($email);

    $mail->isHTML(true);
    $mail->Subject = 'Recuperación de Contraseña';
    $mail->Body = "Hola,<br><br>Para cambiar tu contraseña, haz clic en el siguiente enlace:<br><br>
        <a href='http://localhost:5173/reset-password?token=$token'>Cambiar contraseña</a><br><br>Gracias.";

    $mail->send();

    echo json_encode(['success' => true]);
} catch (Exception $e) {
    echo json_encode(['error' => $mail->ErrorInfo]);
}
?>
