<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header('Content-Type: application/json');

$conexion = new mysqli("localhost", "root", "", "dance");

if ($conexion->connect_error) {
    die(json_encode(['error' => 'Database connection failed']));
}

$fecha = isset($_GET['fecha']) ? $conexion->real_escape_string($_GET['fecha']) : null;

if (!$fecha) {
    echo json_encode(null);
    exit;
}

$query = "SELECT nombre, tipo_clase AS tipo_evento, fecha, hora, profesor,  niveles AS dificultad
          FROM clases 
          WHERE fecha = '$fecha'
          LIMIT 1";

$result = $conexion->query($query);

if ($result && $row = $result->fetch_assoc()) {
    $row['hora'] = $row['hora'] ?? 'Sin hora definida';
    echo json_encode($row);
} else {
    echo json_encode(null);
}

$conexion->close();
?>