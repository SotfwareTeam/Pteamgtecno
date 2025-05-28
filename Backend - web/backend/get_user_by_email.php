<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");

$correo = $_GET['correo'] ?? '';

$conexion = new mysqli('localhost', 'root', '', 'dance'); // Ajusta a tus datos reales

if ($conexion->connect_error) {
    die(json_encode(['error' => 'Database connection failed']));
}

$stmt = $conexion->prepare("SELECT * FROM usuarios WHERE correo = ?");
$stmt->bind_param("s", $correo);
$stmt->execute();
$result = $stmt->get_result();

if ($user = $result->fetch_assoc()) {
    echo json_encode($user);
} else {
    echo json_encode(['error' => 'User not found']);
}
?>
