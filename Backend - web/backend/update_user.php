<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

$host = "localhost";
$user = "root";
$password = "";
$database = "dance";

$connect = new mysqli($host, $user, $password, $database);

if ($connect->connect_errno) {
    http_response_code(500);
    echo json_encode(["error" => "Conexión fallida"]);
    exit;
}

// Obtener datos del JSON enviado
$data = json_decode(file_get_contents("php://input"), true);

$correo = $data['correo'] ?? null;
$nombre = $data['nombre'] ?? null;
$apellido = $data['apellido'] ?? null;
$telefono = $data['telefono'] ?? null;
$direccion = $data['direccion'] ?? null;

if (!$correo) {
    echo json_encode(["error" => "Correo requerido"]);
    exit;
}

$sql = "UPDATE usuarios SET nombre = ?, apellido = ?, telefono = ?, direccion = ? WHERE correo = ?";
$stmt = $connect->prepare($sql);

if (!$stmt) {
    echo json_encode(["error" => "Error en prepare"]);
    exit;
}

$stmt->bind_param("sssss", $nombre, $apellido, $telefono, $direccion, $correo);
$stmt->execute();

if ($stmt->affected_rows > 0) {
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "message" => "No se actualizó ningún dato"]);
}

if (isset($_FILES['imagen'])) {
    file_put_contents("debug_image.log", print_r($_FILES, true));
}


$stmt->close();
$connect->close();
