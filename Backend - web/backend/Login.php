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
            // JOIN login con usuarios para obtener estado y rol
            $sql = "
                SELECT l.id_usuario, l.correo, l.contrasena, l.rol, u.estado
                FROM login l
                JOIN usuarios u ON l.id_usuario = u.id_usuario
                WHERE l.correo = ?
                LIMIT 1
            ";
            $stmt = $connect->prepare($sql);
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $result = $stmt->get_result();

            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();

                if (password_verify($password, $row['contrasena'])) {
                    if ($row['rol'] <= 0 || strtolower($row['estado']) !== 'activo') {
                        echo json_encode(["success" => false, "error" => "Acceso denegado"]);
                        exit;
                    }

                    // Obtener nombre del rol
                    $rolQuery = $connect->prepare("SELECT nombre_rol FROM roles WHERE id_rol = ? LIMIT 1");
                    $rolQuery->bind_param("i", $row['rol']);
                    $rolQuery->execute();
                    $rolQuery->bind_result($nombreRol);
                    $rolQuery->fetch();
                    $rolQuery->close();

                    if (!$nombreRol) {
                        $nombreRol = "desconocido";
                    }

                    echo json_encode([
                        "success" => true,
                        "rol" => strtolower($nombreRol),
                        "correo" => $row['correo'],
                        "id_usuario" => $row['id_usuario']
                    ]);
                } else {
                    echo json_encode(["success" => false, "error" => "Contraseña incorrecta"]);
                }
            } else {
                echo json_encode(["success" => false, "error" => "Usuario no encontrado"]);
            }

            $stmt->close();
        } catch (Exception $e) {
            echo json_encode(["success" => false, "error" => $e->getMessage()]);
        }
    } else {
        echo json_encode(["success" => false, "error" => "Faltan datos"]);
    }
}

$connect->close();
?>
