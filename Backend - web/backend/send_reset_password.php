<?php
date_default_timezone_set('America/Bogota');
ini_set('display_errors', 1);
error_reporting(E_ALL);

// --- Cabeceras
header('Content-Type: application/json');
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

// --- Conexión BD
$conexion = new mysqli("localhost", "root", "", "dance");
if ($conexion->connect_error) {
    echo json_encode(['error' => 'Error de conexión con la base de datos']);
    exit;
}

// --- Leer datos JSON
$data = json_decode(file_get_contents("php://input"), true);
$token = $data['token'] ?? '';
$newPassword = $data['newPassword'] ?? '';

if (empty($token) || empty($newPassword)) {
    echo json_encode(['error' => 'Token y nueva contraseña requeridos']);
    exit;
}

// --- Validar token
$stmt = $conexion->prepare("SELECT correo, created_at FROM password_resets WHERE token = ?");
$stmt->bind_param("s", $token);
$stmt->execute();
$result = $stmt->get_result();

if (!$row = $result->fetch_assoc()) {
    echo json_encode(['error' => 'Token inválido']);
    exit;
}

// --- Validar expiración (1 hora)
$created_at = strtotime($row['created_at']);
if ((time() - $created_at) > 3600) {
    $stmtDelete = $conexion->prepare("DELETE FROM password_resets WHERE token = ?");
    $stmtDelete->bind_param("s", $token);
    $stmtDelete->execute();

    echo json_encode(['error' => 'El token ha expirado.']);
    exit;
}

// --- Actualizar contraseña
$email = trim(strtolower($row['correo']));
$hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);

$stmtUpdate = $conexion->prepare("UPDATE login SET contrasena = ? WHERE TRIM(LOWER(correo)) = ?");
$stmtUpdate->bind_param("ss", $hashedPassword, $email);
$stmtUpdate->execute();

file_put_contents("debug.log", "TOKEN: $token\nEMAIL: $email\nAFFECTED_ROWS: {$stmtUpdate->affected_rows}\n", FILE_APPEND);

if ($stmtUpdate->affected_rows > 0) {
    $stmtDelete = $conexion->prepare("DELETE FROM password_resets WHERE token = ?");
    $stmtDelete->bind_param("s", $token);
    $stmtDelete->execute();

    echo json_encode(['success' => true]);
} else {
    echo json_encode(['error' => 'No se pudo actualizar la contraseña', 'correo' => $email]);
}
