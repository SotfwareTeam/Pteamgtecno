<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

// Conexión MySQL
$mysqli = new mysqli("localhost", "root", "", "dance");

if ($mysqli->connect_error) {
    http_response_code(500);
    echo json_encode(["error" => "Fallo conexión: " . $mysqli->connect_error]);
    exit;
}

// Consultar todos los eventos con fecha y hora
$sql = "SELECT nombre, tipo_evento, fecha, hora, profesor FROM eventos";
$result = $mysqli->query($sql);

$eventos = [];

while ($row = $result->fetch_assoc()) {
    $eventos[] = $row;
}

echo json_encode($eventos);
?>