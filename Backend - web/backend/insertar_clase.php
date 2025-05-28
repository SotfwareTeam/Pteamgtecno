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

if (
    isset($data['fecha']) &&
    isset($data['profesor']) &&
    isset($data['hora']) &&
    isset($data['clase']) &&
    isset($data['dificultad'])
) {
    $fecha = $conexion->real_escape_string($data['fecha']);
    $profesor = $conexion->real_escape_string($data['profesor']);
    $hora = $conexion->real_escape_string($data['hora']);
    $nombre_clase = $conexion->real_escape_string($data['clase']);
    $dificultad = $conexion->real_escape_string($data['dificultad']);

    $tipo_clase = 'Presencial'; // puedes ajustar si quieres hacerlo dinámico
    $duracion = 60; // ejemplo fijo

    // Insertar clase
    $query = "INSERT INTO clases (nombre, tipo_clase, duracion, generos, niveles, fecha, hora, profesor)
              VALUES ('$nombre_clase', '$tipo_clase', '$duracion', 'General', '$dificultad', '$fecha', '$hora', '$profesor')";

    if ($conexion->query($query)) {
        echo json_encode(['success' => true]);
    } else {
        echo json_encode(['error' => 'Insert failed', 'details' => $conexion->error]);
    }
} else {
    echo json_encode(['error' => 'Incomplete data']);
}

$conexion->close();
?>
