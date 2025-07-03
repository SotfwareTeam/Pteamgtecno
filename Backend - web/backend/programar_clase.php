<?php
// Permitir acceso desde cualquier origen
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

require_once 'conexion.php';

// Leer datos JSON
$rawInput = file_get_contents("php://input");
$data = json_decode($rawInput, true);

// Para depurar (opcional)
file_put_contents("debug_input.json", $rawInput); // puedes eliminar esta línea luego

// Validar JSON recibido
$idClase = $data['id_clase'] ?? null;
$idUsuario = $data['id_usuario'] ?? null;

if (!$idClase || !$idUsuario) {
    echo json_encode(['success' => false, 'error' => 'Datos incompletos']);
    exit;
}

try {
    // Validar si ya está programado
    $stmtCheck = $pdo->prepare("SELECT COUNT(*) FROM clases_usuarios WHERE id_clase = ? AND id_usuario = ?");
    $stmtCheck->execute([$idClase, $idUsuario]);
    $yaProgramado = $stmtCheck->fetchColumn();

    if ($yaProgramado > 0) {
        echo json_encode(['success' => false, 'error' => 'Ya estás programado para esta clase']);
        exit;
    }

    // Insertar programación
    $stmt = $pdo->prepare("INSERT INTO clases_usuarios (id_clase, id_usuario) VALUES (?, ?)");
    $stmt->execute([$idClase, $idUsuario]);

    echo json_encode(['success' => true]);
} catch (PDOException $e) {
    // En desarrollo puedes ver el error completo:
    echo json_encode(['success' => false, 'error' => $e->getMessage()]);
    http_response_code(500);
}
?>
