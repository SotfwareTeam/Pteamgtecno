<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Content-Type: application/json");

include 'db.php';
$sql = "
SELECT u.id_usuario, u.nombre, u.apellido, cu.id_clase, c.nombre AS nombre_clase
FROM usuarios u
LEFT JOIN clases_usuarios cu ON cu.id_usuario = u.id_usuario
LEFT JOIN clases c ON c.id_clase = cu.id_clase
WHERE u.id_rol = 3
";


$resultado = mysqli_query($conexion, $sql);

$usuarios = [];

while ($fila = mysqli_fetch_assoc($resultado)) {
    $usuarios[] = $fila;
}

echo json_encode($usuarios); exit;
?>
