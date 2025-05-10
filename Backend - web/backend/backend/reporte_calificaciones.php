<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type, Authorization');
header('Content-Type: application/json');
require 'Conexion.php';

$stmt = $pdo->query("SELECT * FROM reporte_calificaciones");
$calificaciones = $stmt->fetchAll(PDO::FETCH_ASSOC);

echo json_encode($calificaciones);
?>
