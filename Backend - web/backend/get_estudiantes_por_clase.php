<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
header("Access-Control-Allow-Methods: GET");
header("Content-Type: application/json");

include 'db.php'; // usa tu conexión mysqli

$id_clase = $_GET['id_clase'] ?? null;

if (!$id_clase) {
    echo json_encode(['error' => '⚠️ Falta el ID de clase']);
    exit;
}

$sql = "
SELECT u.id_usuario, u.nombre, u.apellido, u.correo, c.nombre AS nombre_clase
FROM clases_usuarios cu
JOIN usuarios u ON cu.id_usuario = u.id_usuario
JOIN clases c ON cu.id_clase = c.id_clase
WHERE cu.id_clase = ?
";

$stmt = $conexion->prepare($sql);
$stmt->bind_param("i", $id_clase);
$stmt->execute();
$result = $stmt->get_result();

$estudiantes = [];
while ($fila = $result->fetch_assoc()) {
    $estudiantes[] = $fila;
}

echo json_encode($estudiantes);
?>