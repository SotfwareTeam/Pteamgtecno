<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Content-Type: application/json");

require_once 'db.php'; // esto define $conexion

$id_usuario = $_GET['id_usuario'] ?? null;
$id_clase = $_GET['id_clase'] ?? null;

if (!$id_usuario || !$id_clase) {
    echo json_encode(['error' => 'Faltan parámetros']);
    exit;
}

// Preparar consulta segura
$sql = "SELECT calificacion, observacion FROM reporte_calificaciones WHERE id_usuario = ? AND id_clase = ?";
$stmt = mysqli_prepare($conexion, $sql);

if (!$stmt) {
    echo json_encode(['error' => 'Error en la preparación de la consulta']);
    exit;
}

mysqli_stmt_bind_param($stmt, "ii", $id_usuario, $id_clase);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);
$data = mysqli_fetch_assoc($result);

// Retornar datos
if ($data) {
    $data['calificacion'] = floatval($data['calificacion']);
    echo json_encode($data);
} else {
    echo json_encode(['calificacion' => 0, 'observacion' => '']);
}

mysqli_stmt_close($stmt);
mysqli_close($conexion);
?>