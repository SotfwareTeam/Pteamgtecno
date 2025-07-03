<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

$conexion = new mysqli("localhost", "root", "", "dance");

if ($conexion->connect_error) {
    die(json_encode(['error' => 'Database connection failed']));
}

$fecha = isset($_GET['fecha']) ? $conexion->real_escape_string($_GET['fecha']) : null;

if (!$fecha) {
    echo json_encode([]);
    exit;
}

$query = "SELECT 
            id_clase, 
            nombre, 
            tipo_clase AS tipo_evento, 
            fecha, 
            hora, 
            profesor
          FROM clases 
          WHERE fecha = '$fecha'";

$result = $conexion->query($query);

$clases = [];

if ($result) {
    while ($row = $result->fetch_assoc()) {
        $row['hora'] = $row['hora'] ?? 'Sin hora definida';
        $clases[] = $row;
    }
}

echo json_encode($clases);

$conexion->close();
