<?php
$host = "localhost";
$user = "root";
$pass = "";
$db   = "dance";

$conexion = mysqli_connect($host, $user, $pass, $db);

// Validación de conexión
if (!$conexion) {
    die("Conexión fallida: " . mysqli_connect_error());
}
?>
    