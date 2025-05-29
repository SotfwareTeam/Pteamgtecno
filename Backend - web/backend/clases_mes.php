<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header('Content-Type: application/json');

$conexion = new mysqli("localhost", "root", "", "dance");

if ($conexion->connect_error) {
    die(json_encode(['error' => 'Database connection failed']));
}

// Mes y Año
$mes = isset($_GET['mes']) ? intval($_GET['mes']) : null;
$anio = isset($_GET['anio']) ? intval($_GET['anio']) : null;

if (!$mes || !$anio) {
    echo json_encode([]);
    exit;
}

// Consulta correcta
$query = "SELECT 
            nombre AS clase,
            tipo_clase AS tipo_evento,
            fecha,
            hora,
            profesor,
            niveles AS dificultad
          FROM clases
          WHERE MONTH(fecha) = $mes AND YEAR(fecha) = $anio";

$result = $conexion->query($query);

$clases = [];
if ($result) {
    while ($row = $result->fetch_assoc()) {
        $clases[] = $row;
    }
}

echo json_encode($clases);

$conexion->close();
?>
