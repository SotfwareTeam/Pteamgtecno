<?php
ini_set('display_errors', 1);
error_reporting(E_ALL);

// --- Cabeceras CORS ---
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");

// --- Manejar preflight OPTIONS ---
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}


$data = json_decode(file_get_contents("php://input"), true);

$token = $data['token'] ?? '';
$newPassword = $data['newPassword'] ?? '';

if (empty($token) || empty($newPassword)) {
    echo json_encode(['error' => 'Token y nueva contraseña requeridos']);
    exit;
}

// Buscar correo por token
$stmt = $conexion->prepare("SELECT email, created_at FROM password_resets WHERE token = ?");
$stmt->bind_param("s", $token);
$stmt->execute();
$result = $stmt->get_result();

if (!$row = $result->fetch_assoc()) {
    echo json_encode(['error' => 'Token inválido o expirado']);
    exit;
}

// Validar tiempo de expiración (opcional, 10 minutos)
$created_at = strtotime($row['created_at']);
if ((time() - $created_at) > 600) {
    $conexion->prepare("DELETE FROM password_resets WHERE token = ?")->bind_param("s", $token)->execute();
    echo json_encode(['error' => 'El token ha expirado.']);
    exit;
}

$email = $row['email'];
$hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);

// Actualizar contraseña en login
$stmtUpdate = $conexion->prepare("UPDATE login SET contrasena = ? WHERE LOWER(correo) = LOWER(?)");
$stmtUpdate->bind_param("ss", $hashedPassword, $email);

if ($stmtUpdate->execute() && $stmtUpdate->affected_rows > 0) {
    $conexion->prepare("DELETE FROM password_resets WHERE token = ?")->bind_param("s", $token)->execute();
    echo json_encode(['success' => true]);
} else {
    echo json_encode(['error' => 'No se pudo actualizar la contraseña']);
}
?>
