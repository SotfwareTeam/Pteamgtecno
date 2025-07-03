<?php
$conexion = new mysqli("localhost", "root", "", "dance");
if ($conexion->connect_error) {
    die("Error de conexión");
}

$correo = 'jpenaquinonez@gmail.com';
$token = bin2hex(random_bytes(32));
$created_at = date('Y-m-d H:i:s');

$stmt = $conexion->prepare("INSERT INTO password_resets (correo, token, created_at) VALUES (?, ?, ?)");
$stmt->bind_param("sss", $correo, $token, $created_at);
$stmt->execute();

echo "✅ Token generado: <br>";
echo "Token: $token<br>";
echo "Enlace: <a href='http://localhost:5173/reset-password?token=$token' target='_blank'>Usar token</a>";
?>
