<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

$conexion = new mysqli("localhost", "root", "", "dance");
if ($conexion->connect_error) {
    echo json_encode(["error" => "Conexión fallida"]);
    exit;
}

$correo    = $_POST['correo']     ?? null;
$nombre    = $_POST['nombre']     ?? null;
$apellido  = $_POST['apellido']   ?? '';
$telefono  = $_POST['telefono']   ?? null;
$direccion = $_POST['direccion']  ?? null;

if (!$correo) {
    echo json_encode(["error" => "Correo requerido"]);
    exit;
}

$imagen_binaria = null;
if (isset($_FILES['imagen']) && $_FILES['imagen']['error'] === 0) {
    $imagen_binaria = file_get_contents($_FILES['imagen']['tmp_name']);
}

if ($imagen_binaria !== null) {
    $sql = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, direccion=?, imagen=? WHERE correo=?";
    $stmt = $conexion->prepare($sql);
    $null = null;
    $stmt->bind_param("ssssbs", $nombre, $apellido, $telefono, $direccion, $null, $correo);
    $stmt->send_long_data(4, $imagen_binaria);
} else {
    $sql = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, direccion=? WHERE correo=?";
    $stmt = $conexion->prepare($sql);
    $stmt->bind_param("sssss", $nombre, $apellido, $telefono, $direccion, $correo);
}

if ($stmt->execute()) {
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "error" => $stmt->error]);
}

$stmt->close();
$conexion->close();
?>