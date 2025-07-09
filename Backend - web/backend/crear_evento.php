<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *'); // Para permitir CORS
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Content-Type');

$input = json_decode(file_get_contents('php://input'), true);

if (!$input) {
    echo json_encode(['error' => 'No se recibió información.']);
    exit;
}

$mysqli = new mysqli("localhost", "root", "", "dance");

if ($mysqli->connect_error) {
    echo json_encode(['error' => 'Conexión fallida']);
    exit;
}

$stmt = $mysqli->prepare("INSERT INTO eventos (nombre, tipo_evento, fecha, hora, profesor) VALUES (?, ?, ?, ?, ?)");
$stmt->bind_param("sssss", $input['nombre'], $input['tipo_evento'], $input['fecha'], $input['hora'], $input['profesor']);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    echo json_encode(['error' => 'No se pudo guardar el evento']);
}

$stmt->close();
$mysqli->close();
?>
