<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Content-Type: application/json");

// Preflight
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

// Conexión PDO
include 'Conexion.php'; // asegúrate que este define $pdo con PDO

// Leer datos del frontend
$data = json_decode(file_get_contents("php://input"), true);

// Validación básica
if (!$data || !isset($data['id_usuario'], $data['id_clase'], $data['asistencia'])) {
    echo json_encode(['error' => '❌ Datos incompletos']);
    exit();
}

$id_usuario = $data['id_usuario'];
$id_clase = $data['id_clase'];
$asistencia = $data['asistencia'] ? 1 : 0;
$fecha = date('Y-m-d');

try {
    $sql = "INSERT INTO reporte_asistencias (id_usuario, id_clase, asistencia, fecha)
            VALUES (?, ?, ?, ?)";
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$id_usuario, $id_clase, $asistencia, $fecha]);

    echo json_encode(['message' => '✅ Asistencia guardada']);
} catch (PDOException $e) {
    echo json_encode(['error' => '❌ Error al insertar: ' . $e->getMessage()]);
}
