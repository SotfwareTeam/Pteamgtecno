<?php
// CORS y headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

include 'conexion.php'; // conexión PDO

$data = json_decode(file_get_contents('php://input'), true);

$id_usuario = $data['id_usuario'] ?? null;
$id_clase = $data['id_clase'] ?? null;
$calificacion = $data['calificacion'] ?? null;
$promedio = $data['promedio'] ?? null;
$fecha = date('Y-m-d');

if ($id_usuario && $id_clase && $calificacion !== null) {
    // INSERTAR o ACTUALIZAR si ya existe
    $stmt = $pdo->prepare("
        INSERT INTO reporte_calificaciones (id_usuario, id_clase, calificacion, promedio, fecha)
        VALUES (?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
            calificacion = VALUES(calificacion),
            promedio = VALUES(promedio),
            fecha = VALUES(fecha)
    ");
    $stmt->execute([$id_usuario, $id_clase, $calificacion, $promedio, $fecha]);

    echo json_encode(['message' => '✅ Calificación guardada/actualizada']);
} else {
    echo json_encode(['error' => '❌ Datos incompletos']);
}
?>
