<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

$correo = $_GET['correo'] ?? '';

if (!$correo) {
    echo json_encode(['error' => 'Correo requerido']);
    exit;
}

$conexion = new mysqli("localhost", "root", "", "dance");

if ($conexion->connect_error) {
    echo json_encode(['error' => 'Conexión fallida']);
    exit;
}

$stmt = $conexion->prepare("SELECT nombre, apellido, telefono, direccion, correo, imagen FROM usuarios WHERE correo = ?");
$stmt->bind_param("s", $correo);
$stmt->execute();
$result = $stmt->get_result();

if ($user = $result->fetch_assoc()) {
    if (!empty($user['imagen'])) {
        // Detectar tipo MIME real de la imagen
        $finfo = new finfo(FILEINFO_MIME_TYPE);
        $mimeType = $finfo->buffer($user['imagen']) ?: 'image/jpeg';

        $user['imagen'] = 'data:' . $mimeType . ';base64,' . base64_encode($user['imagen']);
    } else {
        $user['imagen'] = null;
    }

    echo json_encode($user);
} else {
    echo json_encode(['error' => 'Usuario no encontrado']);
}

$stmt->close();
$conexion->close();
