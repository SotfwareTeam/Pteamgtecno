<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header('Content-Type: application/json');

// Conexión a la base de datos
$conexion = new mysqli('localhost', 'root', '', 'dance');

if ($conexion->connect_error) {
    die(json_encode(['error' => 'Database connection failed']));
}

// Recibir datos
$data = json_decode(file_get_contents("php://input"), true);

$correo = $data['email'] ?? '';
$nombre = $data['name'] ?? '';
$telefono = $data['phone'] ?? '';
$direccion = $data['address'] ?? '';

if (empty($correo)) {
    echo json_encode(['error' => 'Email is required']);
    exit;
}

// Actualizar datos
$stmt = $conexion->prepare("UPDATE usuarios SET nombre = ?, telefono = ?, direccion = ? WHERE correo = ?");
$stmt->bind_param("ssss", $nombre, $telefono, $direccion, $correo);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    echo json_encode(['success' => false, 'error' => $conexion->error]);
}
?>
