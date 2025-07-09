<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Content-Type');

$input = json_decode(file_get_contents("php://input"), true);

if (!isset($input['fecha'])) {
    echo json_encode(['error' => 'Fecha no recibida']);
    exit;
}

$mysqli = new mysqli("localhost", "root", "", "dance");

if ($mysqli->connect_error) {
    echo json_encode(['error' => 'Conexión fallida']);
    exit;
}

$stmt = $mysqli->prepare("DELETE FROM eventos WHERE fecha = ?");
$stmt->bind_param("s", $input['fecha']);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    echo json_encode(['error' => 'No se pudo eliminar']);
}

$stmt->close();
$mysqli->close();
?>