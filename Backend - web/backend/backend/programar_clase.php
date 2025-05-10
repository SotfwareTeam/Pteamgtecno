<?php
// Encabezados CORS y tipo de contenido JSON
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Content-Type");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Content-Type: application/json");

// Mostrar errores
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Preflight CORS
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

// Conexión
$connect = new mysqli("localhost", "root", "", "dance");
if ($connect->connect_error) {
    echo json_encode(["error" => "Error de conexión: " . $connect->connect_error]);
    exit();
}

// POST
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $input = file_get_contents("php://input");
    $data = json_decode($input, true);

    $fecha = $data['fecha'] ?? null;
    $hora = $data['hora'] ?? null;
    $profesor = $data['profesor'] ?? null;
    $sede = $data['sede'] ?? null;
    $clase = $data['clase'] ?? null;
    $dificultad = $data['dificultad'] ?? null;

    if (!$fecha || !$hora || !$profesor || !$sede || !$clase || !$dificultad) {
        echo json_encode(["error" => "Todos los campos son obligatorios"]);
        exit();
    }

    $stmt = $connect->prepare("
        INSERT INTO eventos (fecha, hora, profesor, sede, nombre, dificultad, tipo_evento)
        VALUES (?, ?, ?, ?, ?, ?, 'Clase Programada')
    ");

    if (!$stmt) {
        echo json_encode(["error" => "Error en prepare: " . $connect->error]);
        exit();
    }

    $stmt->bind_param("ssssss", $fecha, $hora, $profesor, $sede, $clase, $dificultad);

    if ($stmt->execute()) {
        echo json_encode(["message" => "Clase guardada con éxito"]);
    } else {
        echo json_encode(["error" => "Error en ejecución: " . $stmt->error]);
    }

    $stmt->close();
    $connect->close();
    exit();
}

// Otro método (no POST)
echo json_encode(["error" => "Método no permitido"]);
exit();
    