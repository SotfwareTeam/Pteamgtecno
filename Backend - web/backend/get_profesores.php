<?php
header("Access-Control-Allow-Origin: *");
header('Content-Type: application/json');

$conexion = new mysqli("localhost", "root", "", "dance");

if ($conexion->connect_error) {
    echo json_encode(["error" => "Conexión fallida"]);
    exit;
}

// Solo usuarios con rol de profesor (id_rol = 2)
$query = "SELECT id_usuario, nombre FROM usuarios WHERE id_rol = 2";

$resultado = $conexion->query($query);

$profesores = [];
if ($resultado) {
    while ($row = $resultado->fetch_assoc()) {
        $profesores[] = $row;
    }
}

echo json_encode($profesores);
$conexion->close();

?>