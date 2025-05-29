<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
require 'conexion.php'; // tu archivo de conexión

$sql = "SELECT id_usuario, nombre, apellido, correo, telefono FROM usuarios WHERE id_rol = 2 AND estado = 'activo'";
$result = $conn->query($sql);

$profesores = [];

while ($row = $result->fetch_assoc()) {
    $profesores[] = $row;
}

header('Content-Type: application/json');
echo json_encode($profesores);
?>
