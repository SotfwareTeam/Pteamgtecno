<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

require_once 'conexion.php'; // ← Esto define $pdo

$data = json_decode(file_get_contents("php://input"), true);

$idClase = $data['id_clase'] ?? null;
$idUsuario = $data['id_usuario'] ?? null;

if ($idClase && $idUsuario) {
    try {
        $stmt = $pdo->prepare("INSERT INTO clases_usuarios (id_clase, id_usuario) VALUES (?, ?)");
        $stmt->execute([$idClase, $idUsuario]);

        echo json_encode(['success' => true]);
    } catch (PDOException $e) {
        echo json_encode(['success' => false, 'error' => $e->getMessage()]);
    }
} else {
    echo json_encode(['success' => false, 'error' => 'Datos incompletos']);
}
?>