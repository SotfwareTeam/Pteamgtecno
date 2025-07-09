<?php
require_once './vendor/autoload.php';

use Firebase\JWT\JWT;
use Firebase\JWT\Key;

function verifyToken() {
    $headers = getallheaders();

    if (!isset($headers['Authorization'])) {
        http_response_code(401);
        echo json_encode(["success" => false, "error" => "No hay token"]);
        exit;
    }

    $token = $headers['Authorization'];

    try {
        $key = "keyProvisional123";
        $decoded = JWT::decode($token, new Key($key, 'HS256'));

        return (array) $decoded;

    } catch (Exception $e) {
        http_response_code(401);
        echo json_encode(["success" => false, "error" => "Token no válido", "detail" => $e->getMessage()]);
        exit;
    }
}
