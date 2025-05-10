<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");

require 'vendor/autoload.php';
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

$conexion = new mysqli('localhost', 'root', '', 'dance');
if ($conexion->connect_error) {
    echo json_encode(['error' => 'Error de conexión a la base de datos']);
    exit;
}
// Limpiar tokens expirados (opcional: más de 10 minutos)
$conexion->query("DELETE FROM password_resets WHERE created_at < (NOW() - INTERVAL 10 MINUTE)");


$data = json_decode(file_get_contents("php://input"), true);
$email = $data['email'] ?? '';

if (empty($email)) {
    echo json_encode(['error' => 'Correo requerido']);
    exit;
}

// Buscar en tabla login
$stmtLogin = $conexion->prepare("SELECT * FROM login WHERE correo = ?");
$stmtLogin->bind_param("s", $email);
$stmtLogin->execute();
$resultLogin = $stmtLogin->get_result();

// Buscar en tabla usuarios
$stmtUsuarios = $conexion->prepare("SELECT * FROM usuarios WHERE correo = ?");
$stmtUsuarios->bind_param("s", $email);
$stmtUsuarios->execute();
$resultUsuarios = $stmtUsuarios->get_result();

if (!$resultLogin->fetch_assoc() && !$resultUsuarios->fetch_assoc()) {
    echo json_encode(['error' => 'Correo no registrado en el sistema']);
    exit;
}

// Crear token
$token = bin2hex(random_bytes(50));

// Insertar token
$stmtInsert = $conexion->prepare("INSERT INTO password_resets (email, token) VALUES (?, ?)");
$stmtInsert->bind_param("ss", $email, $token);
$stmtInsert->execute();

// Enviar correo
$mail = new PHPMailer(true);

try {
    $USE_MAILTRAP = false; // true si quieres usar Mailtrap, false para Gmail

    if ($USE_MAILTRAP) {
        $mail->isSMTP();
        $mail->Host = 'sandbox.smtp.mailtrap.io';
        $mail->SMTPAuth = true;
        $mail->Username = 'MAILTRAP_USERNAME';
        $mail->Password = 'MAILTRAP_PASSWORD';
        $mail->Port = 2525;
        $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
    } else {
        $mail->isSMTP();
        $mail->Host = 'smtp.gmail.com';
        $mail->SMTPAuth = true;
        $mail->Username = 'jpenaquinonez@gmail.com'; // tu correo
        $mail->Password = 'jzhweywymthjxrop'; // tu contraseña de app
        $mail->Port = 587;
        $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
    }

    $mail->setFrom('no-reply@tudominio.com', 'Academia de Baile');
    $mail->addAddress($email);
    $mail->isHTML(true);
    $mail->Subject = 'Recuperación de Contraseña';
    $mail->Body = "
        <p>Hola,</p>
        <p>Haz clic en el siguiente enlace para restablecer tu contraseña:</p>
        <p><a href='http://localhost:5173/ResetPassword?token=$token'>Restablecer Contraseña</a></p>
        <p>Este enlace expirará pronto.</p>
    ";

    $mail->send();
    echo json_encode(['success' => true]);
} catch (Exception $e) {
    echo json_encode(['error' => 'Error al enviar correo: ' . $mail->ErrorInfo]);
}
?>
