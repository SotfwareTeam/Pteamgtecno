<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

include 'db.php';

$sql = "SELECT id_rol, nombre_rol FROM roles WHERE id_rol > 0";
$result = mysqli_query($conexion, $sql);

$roles = [];

while ($row = mysqli_fetch_assoc($result)) {
    $roles[] = $row;
}

echo json_encode($roles);
?>
