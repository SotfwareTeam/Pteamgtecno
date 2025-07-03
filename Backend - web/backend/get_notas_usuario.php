<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

$conexion = new mysqli("localhost", "root", "", "dance");

if ($conexion->connect_error) {
    echo json_encode(['error' => 'Error de conexión']);
    exit;
}

$id_usuario = $_GET['id_usuario'] ?? null;
if (!$id_usuario) {
    echo json_encode([]); // ← aquí antes devolvía objeto, ahora un array vacío
    exit;
}

$sql = "
    SELECT c.nombre_clase, r.calificacion
    FROM reporte_calificaciones r
    INNER JOIN clases c ON r.id_clase = c.id_clase
    WHERE r.id_usuario = ?
";

$stmt = $conexion->prepare($sql);
$stmt->bind_param("i", $id_usuario);
$stmt->execute();
$result = $stmt->get_result();

$notas = [];
while ($row = $result->fetch_assoc()) {
    $notas[] = [
        'name' => $row['nombre_clase'],
        'grade' => $row['calificacion']
    ];
}

echo json_encode($notas); // ← siempre retorna array

$stmt->close();
$conexion->close();
?>
