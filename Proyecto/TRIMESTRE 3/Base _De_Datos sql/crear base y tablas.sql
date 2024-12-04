CREATE DATABASE Dance;
USE Dance;

-- Tabla de roles
CREATE TABLE roles (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(50) NOT NULL
);

-- Tabla de usuarios
CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    id_rol INT,
    estado TINYINT(1) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    tipo_documento VARCHAR(30) NOT NULL,
    numero_documento VARCHAR(15) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    contraseña VARBINARY(2000) NOT NULL,
    sexo VARCHAR(20),
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    fecha_nacimiento DATE,
    fecha_creacion DATE,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

-- Tabla de clases
CREATE TABLE clases (
    id_clase INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipo_clase VARCHAR(20),
    duracion INT,
    generos VARCHAR(100),
    niveles VARCHAR(20),
    fecha DATE
);

-- Tabla intermedia para clases y usuarios
CREATE TABLE clases_usuarios (
    id_clase INT,
    id_usuario INT,
    PRIMARY KEY (id_clase, id_usuario),
    FOREIGN KEY (id_clase) REFERENCES clases(id_clase),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabla de reportes de asistencias
CREATE TABLE reporte_asistencias (
    id_reporte_asistencia INT PRIMARY KEY AUTO_INCREMENT,
    id_clase INT,
    id_usuario INT,
    asistencia TINYINT(1),
    FOREIGN KEY (id_clase) REFERENCES clases(id_clase),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabla de reportes de calificaciones
CREATE TABLE reporte_calificaciones (
    id_reporte_calificacion INT PRIMARY KEY AUTO_INCREMENT,
    id_clase INT,
    id_usuario INT,
    calificacion INT,
    promedio FLOAT,
    fecha DATE,
    FOREIGN KEY (id_clase) REFERENCES clases(id_clase),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabla de notificaciones
CREATE TABLE notificaciones (
    id_notificacion INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    mensaje CHAR(255) NOT NULL,
    fecha_envio DATETIME,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- Tabla de eventos
CREATE TABLE eventos (
    id_evento INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    tipo_evento VARCHAR(255),
    fecha DATE
);

-- Tabla intermedia para eventos y usuarios
CREATE TABLE eventos_usuarios (
    id_evento INT,
    id_usuario INT,
    PRIMARY KEY (id_evento, id_usuario),
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);
