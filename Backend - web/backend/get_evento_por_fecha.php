<?php
// Habilita CORS completamente (solo en desarrollo)
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

// Manejo de preflight request
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

header('Content-Type: application/json');

// JSON body
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

$stmt = $mysqli->prepare("SELECT nombre, tipo_evento, fecha, hora, profesor FROM eventos WHERE fecha = ?");
$stmt->bind_param("s", $input['fecha']);
$stmt->execute();
$result = $stmt->get_result();

if ($row = $result->fetch_assoc()) {
    echo json_encode($row);
} else {
    echo json_encode(null);
}

$stmt->close();
$mysqli->close();
?>