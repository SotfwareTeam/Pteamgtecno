<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

$conexion = new mysqli('localhost', 'root', '', 'dance');

if ($conexion->connect_error) {
    die(json_encode(['error' => 'Error de conexión a la base de datos']));
}

$data = json_decode(file_get_contents("php://input"), true);

$token = $data['token'] ?? '';
$newPassword = $data['newPassword'] ?? '';

if (empty($token) || empty($newPassword)) {
    echo json_encode(['error' => 'Token y nueva contraseña requeridos']);
    exit;
}

// Buscar el correo asociado al token
$stmt = $conexion->prepare("SELECT email FROM password_resets WHERE token = ?");
$stmt->bind_param("s", $token);
$stmt->execute();
$result = $stmt->get_result();

if (!$row = $result->fetch_assoc()) {
    echo json_encode(['error' => 'Token inválido o expirado']);
    exit;
}

$email = $row['email'];

// Ahora actualizamos la contraseña en la tabla `login`
$hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);

$stmtUpdate = $conexion->prepare("UPDATE login SET contrasena = ? WHERE correo = ?");
$stmtUpdate->bind_param("ss", $hashedPassword, $email);

if ($stmtUpdate->execute()) {
    // Borramos el token usado
    $stmtDelete = $conexion->prepare("DELETE FROM password_resets WHERE token = ?");
    $stmtDelete->bind_param("s", $token);
    $stmtDelete->execute();

    echo json_encode(['success' => true]);
} else {
    echo json_encode(['error' => 'No se pudo actualizar la contraseña']);
}
?>
