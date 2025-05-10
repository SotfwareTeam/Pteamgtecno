<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

$host = "localhost";
$user = "root";
$password = "";
$data_base = "dance";

$connect = new mysqli($host, $user, $password, $data_base);

if ($connect->connect_errno) {
    die("Conexion fallida" . $connect->connect_errno);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $input = file_get_contents('php://input');
    $data = json_decode($input, true);

    $email = $data['email'] ?? null;
    $password = $data['password'] ?? null;

    if ($email && $password) {
        try {
            // Solo filtra por correo
            $sql = "SELECT correo, contrasena, rol FROM login WHERE correo = ?";
            $stmt = $connect->prepare($sql);
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $result = $stmt->get_result();

            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();

                // Verifica la contraseña con password_verify
                if (password_verify($password, $row['contrasena'])) {
                    echo json_encode([
                        "rol" => strtolower($row['rol']),
                        "correo" => $row['correo']
                    ]);
                } else {
                    echo json_encode(["rol" => "noresult"]);
                }
            } else {
                echo json_encode(["rol" => "noresult"]);
            }

            $stmt->close();
        } catch (Exception $e) {
            echo "Error al buscar los datos:" . $e->getMessage();
        }
    } else {
        echo "Faltan datos";
    }
}

$connect->close();
