<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

$connect = new mysqli("localhost", "root", "", "dance");

$fecha = $_GET['fecha'] ?? null;

if (!$fecha) {
    echo json_encode(["error" => "Fecha requerida"]);
    exit();
}

$stmt = $connect->prepare("SELECT * FROM eventos WHERE fecha = ? AND tipo_evento = 'Clase Programada'");
$stmt->bind_param("s", $fecha);
$stmt->execute();
$result = $stmt->get_result();

if ($row = $result->fetch_assoc()) {
    echo json_encode($row);
} else {
    echo json_encode(null); // No se encontró clase
}

$stmt->close();
$connect->close();
