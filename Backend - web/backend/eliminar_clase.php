<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header('Content-Type: application/json');

// Conexión a la base de datos
$conexion = new mysqli("localhost", "root", "", "dance");

if ($conexion->connect_error) {
    die(json_encode(['error' => 'Database connection failed']));
}

// Obtener el cuerpo del POST
$data = json_decode(file_get_contents('php://input'), true);

if (isset($data['fecha'])) {
    $fecha = $conexion->real_escape_string($data['fecha']);

    // Eliminar clases del día específico
    $query = "DELETE FROM clases WHERE fecha = '$fecha'";

    if ($conexion->query($query)) {
        echo json_encode(['success' => true]);
    } else {
        echo json_encode(['error' => 'Delete failed']);
    }
} else {
    echo json_encode(['error' => 'No date provided']);
}

$conexion->close();
?>
