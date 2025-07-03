<?php
// Manejo de CORS preflight
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: Content-Type");
    header("Access-Control-Allow-Methods: POST, OPTIONS");
    http_response_code(200);
    exit;
}

// CORS y tipo de contenido
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Methods: POST");
header("Content-Type: application/json");

// Conexión DB
include 'db.php';

// Leer JSON
$data = json_decode(file_get_contents("php://input"), true);

// Validación
if (!isset($data['id_usuario'], $data['estado'], $data['id_rol'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Datos incompletos']);
    exit;
}

$id_usuario = intval($data['id_usuario']);
$estado = $conexion->real_escape_string($data['estado']);
$id_rol = intval($data['id_rol']);

// Preparar la consulta segura
$stmt = $conexion->prepare("UPDATE usuarios SET estado = ?, id_rol = ? WHERE id_usuario = ?");
$stmt->bind_param("sii", $estado, $id_rol, $id_usuario);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    http_response_code(500);
    echo json_encode(['error' => $stmt->error]);
}

$stmt->close();
$conexion->close();
?>
