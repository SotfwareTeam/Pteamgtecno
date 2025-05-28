<?php
// CORS
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

// DB connection
$host = "localhost";
$user = "root";
$password = "";
$database = "dance";

$connect = new mysqli($host, $user, $password, $database);
if ($connect->connect_errno) {
    error_log("Error de conexión: " . $connect->connect_error);
    die(json_encode(["success" => false, "error" => "Conexión fallida"]));
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $input = file_get_contents("php://input");
    $data = json_decode($input, true);

    $rol = $data['rol'] ?? 2; // <- corregido
    $nombre = $data['name'] ?? null;
    $apellido = $data['surname'] ?? null;
    $sexo = $data['gender'] ?? null;
    $tipo_doc = $data['identification'] ?? null;
    $num_doc = $data['identification_number'] ?? null;
    $direccion = $data['address'] ?? null;
    $telefono = $data['phone'] ?? null;
    $correo = $data['remail'] ?? null;
    $clave = $data['rpassword'] ?? null;
    $imagen = $data['imagen'] ?? null;
    $estado = "activo";
    $fecha_creacion = date("Y-m-d");

    if (!$nombre || !$apellido || !$sexo || !$tipo_doc || !$num_doc || !$telefono || !$correo || !$clave || !$rol) {
        echo json_encode(["success" => false, "error" => "Faltan campos obligatorios"]);
        exit;
    }

    try {
        $claveHash = password_hash($clave, PASSWORD_DEFAULT);

        $sqlUsuarios = "INSERT INTO usuarios (id_rol, nombre, apellido, sexo, tipo_documento, numero_documento, direccion, telefono, correo, estado, fecha_creacion, imagen)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        $stmt1 = $connect->prepare($sqlUsuarios);
        if (!$stmt1) {
            throw new Exception("Prepare usuarios: " . $connect->error);
        }
        $stmt1->bind_param("isssssisssss", $rol, $nombre, $apellido, $sexo, $tipo_doc, $num_doc, $direccion, $telefono, $correo, $estado, $fecha_creacion, $imagen);
        $stmt1->execute();
        $id_usuario = $connect->insert_id;
        $stmt1->close();

        $sqlLogin = "INSERT INTO login (correo, contrasena, rol, id_usuario) VALUES (?, ?, ?, ?)";
        $stmt2 = $connect->prepare($sqlLogin);
        if (!$stmt2) {
            throw new Exception("Prepare login: " . $connect->error);
        }
        $stmt2->bind_param("sssi", $correo, $claveHash, $rol, $id_usuario);
        $stmt2->execute();  
        if ($stmt2->error) {
            throw new Exception("Error al insertar en login: " . $stmt2->error);
        }
        $stmt2->close();

        echo json_encode(["success" => true, "message" => "Registro completado correctamente"]);
    } catch (Exception $e) {
        error_log("Error en registro: " . $e->getMessage());
        echo json_encode(["success" => false, "error" => $e->getMessage()]);
    }
}

$connect->close();
