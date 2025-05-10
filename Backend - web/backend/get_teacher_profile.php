<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

$host = "localhost";
$user = "root";
$password = "";
$database = "dance";

$conn = new mysqli($host, $user, $password, $database);

if ($conn->connect_error) {
    die(json_encode([
        'success' => false,
        'error' => 'Connection failed: ' . $conn->connect_error
    ]));
}

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $email = $_GET['email'] ?? '';
    
    $stmt = $conn->prepare("SELECT id_usuario, nombre, apellido, correo, telefono, direccion, id_rol 
                           FROM usuarios 
                           WHERE correo = ? AND id_rol = 3");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $result = $stmt->get_result();
    
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        echo json_encode([
            'success' => true,
            'id' => $row['id_usuario'],
            'name' => $row['nombre'] . ' ' . $row['apellido'],
            'email' => $row['correo'],
            'phone' => $row['telefono'],
            'address' => $row['direccion'],
            'rol' => $row['id_rol']
        ]);
    } else {
        echo json_encode([
            'success' => false,
            'error' => 'Profesor no encontrado'
        ]);
    }
    
    $stmt->close();
}

$conn->close();
?>