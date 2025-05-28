<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');
header('Content-Type: application/json');

include 'Conexion.php';

try {
    $stmt = $pdo->query("SELECT * FROM reporte_asistencias");
    $asistencias = $stmt->fetchAll(PDO::FETCH_ASSOC);

    echo json_encode($asistencias);
} catch (PDOException $e) {
    echo json_encode(['error' => $e->getMessage()]);
}
?>
