<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

// Forzar respuesta JSON
header('Content-Type: application/json');
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");

// Manejo global de errores
set_exception_handler(function($e) {
    http_response_code(500);
    echo json_encode(['error' => 'Excepción no capturada: ' . $e->getMessage()]);
    exit;
});

set_error_handler(function($errno, $errstr, $errfile, $errline) {
    http_response_code(500);
    echo json_encode(['error' => "Error: $errstr en $errfile línea $errline"]);
    exit;
});

require 'vendor/autoload.php';
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

// Conexión a base de datos
$conexion = new mysqli('localhost', 'root', '', 'dance');
if ($conexion->connect_error) {
    echo json_encode(['error' => 'Error de conexión a la base de datos']);
    exit;
}

// Limpiar tokens expirados (opcional)
$conexion->query("DELETE FROM password_resets WHERE created_at < (NOW() - INTERVAL 10 MINUTE)");

// Leer datos del body JSON
$data = json_decode(file_get_contents("php://input"), true);
$Correo = $data['Correo'] ?? '';

if (empty($Correo)) {
    echo json_encode(['error' => 'Correo requerido']);
    exit;
}

// Verificar si el correo existe
$stmtLogin = $conexion->prepare("SELECT * FROM login WHERE correo = ?");
$stmtLogin->bind_param("s", $Correo);
$stmtLogin->execute();
$resultLogin = $stmtLogin->get_result();

$stmtUsuarios = $conexion->prepare("SELECT * FROM usuarios WHERE correo = ?");
$stmtUsuarios->bind_param("s", $Correo);
$stmtUsuarios->execute();
$resultUsuarios = $stmtUsuarios->get_result();

if (!$resultLogin->fetch_assoc() && !$resultUsuarios->fetch_assoc()) {
    echo json_encode(['error' => 'Correo no registrado en el sistema']);
    exit;
}

// Crear token y guardar
$token = bin2hex(random_bytes(50));
$stmtInsert = $conexion->prepare("INSERT INTO password_resets (Correo, token) VALUES (?, ?)");
$stmtInsert->bind_param("ss", $Correo, $token);
$stmtInsert->execute();

// Configurar PHPMailer
$mail = new PHPMailer(true);

try {
    $USE_MAILTRAP = false; // Cambiar según lo necesites

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
        $mail->Username = 'sotfwareteam@gmail.com'; // tu correo
        $mail->Password = 'utvpbtmvdtuyaech'; // tu contraseña de app
        $mail->Port = 587;
        $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
    }

    // Depuración opcional
    // $mail->SMTPDebug = 2;
    // $mail->Debugoutput = function($str, $level) {
    //     error_log("SMTP: $str");
    // };

    $mail->setFrom('no-reply@tudominio.com', 'Academia de Baile');
    $mail->addAddress($Correo);
    $mail->CharSet = 'UTF-8';
$mail->isHTML(true);
$mail->Subject = '🔒 Recuperación de Contraseña';

$mail->Body = "
<!DOCTYPE html>
<html lang='es'>
<head>
  <meta charset='UTF-8'>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      padding: 20px;
      margin: 0;
    }
    .container {
      max-width: 600px;
      background-color: #fff;
      padding: 25px;
      border-radius: 10px;
      margin: auto;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .header {
      background-color: #9854b1;
      color: white;
      padding: 15px;
      text-align: center;
      font-size: 20px;
      border-radius: 8px 8px 0 0;
    }
    .content {
      margin-top: 20px;
      font-size: 16px;
      color: #333;
    }
    .btn {
      display: inline-block;
      margin-top: 20px;
      padding: 12px 20px;
      background-color: #9854b1;
      color: white;
      text-decoration: none;
      border-radius: 5px;
    }
    .footer {
      margin-top: 30px;
      font-size: 12px;
      color: #777;
      text-align: center;
    }
  </style>
</head>
<body>
  <div class='container'>
    <div class='header'>Recuperación de Contraseña</div>
    <div class='content'>
      <p>Hola,</p>
      <p>Haz clic en el siguiente botón para restablecer tu contraseña:</p>
      <p style='text-align: center;'>
        <a href='http://localhost:5173/ResetPassword?token=$token' 
   style='
     display: inline-block;
     background-color: #6a1b9a;
     color: white;
     padding: 14px 28px;
     text-decoration: none;
     font-weight: bold;
     border-radius: 6px;
     font-size: 16px;
     box-shadow: 0 4px 8px rgba(0,0,0,0.15);
     transition: background 0.3s ease;
   '
   onmouseover=\"this.style.backgroundColor='#4a148c'\"
   onmouseout=\"this.style.backgroundColor='#6a1b9a'\"
>
  🔒 Restablecer Contraseña
</a>
      </p>
      <p>Este enlace expirará en unos minutos por seguridad.</p>
    </div>
    <div class='footer'>
      © " . date('Y') . " Academia de Baile. Todos los derechos reservados.
    </div>
  </div>
</body>
</html>
";

$mail->AltBody = "Hola,\n\nVisita este enlace para restablecer tu contraseña:\nhttp://localhost:5173/ResetPassword?token=$token\n\nEste enlace expirará pronto.";


    $mail->send();
    echo json_encode(['success' => true]);
} catch (Exception $e) {
    echo json_encode(['error' => 'Error al enviar correo: ' . $mail->ErrorInfo]);
}
