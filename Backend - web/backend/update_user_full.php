<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

$connect = new mysqli("localhost", "root", "", "dance");

if ($connect->connect_error) {
    http_response_code(500);
    echo json_encode(["error" => "Conexión fallida"]);
    exit;
}

$correo     = $_POST['correo'] ?? null;
$nombre     = $_POST['nombre'] ?? null;
$apellido   = $_POST['apellido'] ?? null;
$telefono   = $_POST['telefono'] ?? null;
$direccion  = $_POST['direccion'] ?? null;

if (!$correo) {
    echo json_encode(["error" => "Correo requerido"]);
    exit;
}

// Procesar imagen si existe
if (isset($_FILES['imagen']) && $_FILES['imagen']['error'] === 0) {
    $imagen_binaria = file_get_contents($_FILES['imagen']['tmp_name']);

    $sql = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, direccion=?, imagen=? WHERE correo=?";
    $stmt = $connect->prepare($sql);
    if (!$stmt) {
        echo json_encode(["success" => false, "error" => $connect->error]);
        exit;
    }

    // Usa variable dummy para BLOB y luego la reemplazas con send_long_data
    $stmt->bind_param("sssbss", $nombre, $apellido, $telefono, $direccion, $imagen_binaria, $correo);
    $stmt->send_long_data(4, $imagen_binaria); // posición 4 = imagen (índice empieza en 0)

    // Ejecutar y verificar
    if ($stmt->execute()) {
        echo json_encode(["success" => true]);
    } else {
        echo json_encode(["success" => false, "error" => $stmt->error]);
    }

    $stmt->close();
} else {
    // Sin imagen
    $sql = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, direccion=? WHERE correo=?";
    $stmt = $connect->prepare($sql);
    if (!$stmt) {
        echo json_encode(["success" => false, "error" => $connect->error]);
        exit;
    }

    $stmt->bind_param("sssss", $nombre, $apellido, $telefono, $direccion, $correo);
    if ($stmt->execute()) {
        echo json_encode(["success" => true]);
    } else {
        echo json_encode(["success" => false, "error" => $stmt->error]);
    }

    $stmt->close();
}

$connect->close();
?>