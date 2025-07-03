<?php
header('Access-Control-Allow-Origin: *'); // <--- ESTO ES IMPORTANTE
header('Content-Type: application/json');
$host = 'localhost';
$db   = 'dance';
$user = 'root';
$pass = '';
$charset = 'utf8mb4';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$db;charset=utf8mb4", $user, $pass);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $stmt = $pdo->query("SELECT DISTINCT fecha FROM clases WHERE fecha IS NOT NULL ORDER BY fecha ASC");
    $fechas = $stmt->fetchAll(PDO::FETCH_COLUMN);

    echo json_encode($fechas);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => 'Error al conectar con la base de datos: ' . $e->getMessage()]);
}
?>