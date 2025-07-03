<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Content-Type: application/json");

include 'db.php';

$sql = "
SELECT 
    u.id_usuario,
    u.nombre,
    u.apellido,
    u.estado,
    r.nombre_rol,
    ROUND(AVG(rc.calificacion), 2) AS nota_promedio
FROM usuarios u
LEFT JOIN roles r ON u.id_rol = r.id_rol
LEFT JOIN reporte_calificaciones rc ON rc.id_usuario = u.id_usuario
WHERE u.id_usuario IS NOT NULL
GROUP BY u.id_usuario
";

$resultado = mysqli_query($conexion, $sql);
$usuarios = [];

while ($fila = mysqli_fetch_assoc($resultado)) {
    $usuarios[] = $fila;
}

echo json_encode($usuarios);
exit;
?>
