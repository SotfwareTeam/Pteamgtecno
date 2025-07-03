<?php
header("Access-Control-Allow-Origin: *");

$conexion = new mysqli("localhost", "root", "", "dance");

$correo = $_GET['correo'] ?? '';
if (!$correo) {
    http_response_code(400);
    exit("Correo requerido");
}

$stmt = $conexion->prepare("SELECT imagen FROM usuarios WHERE correo = ?");
$stmt->bind_param("s", $correo);
$stmt->execute();
$stmt->store_result();
$stmt->bind_result($imagen);
$stmt->fetch();

if ($imagen) {
    // Detectar tipo MIME automáticamente (jpg, png, etc.)
    $finfo = new finfo(FILEINFO_MIME_TYPE);
    $mime = $finfo->buffer($imagen);
    header("Content-Type: $mime");

    echo $imagen;
} else {
    http_response_code(404);
    exit("Imagen no encontrada");
}

$stmt->close();
$conexion->close();
?>