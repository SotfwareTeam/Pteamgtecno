-- Insertar roles
INSERT INTO roles (nombre_rol) VALUES 
('Administrador'), 
('Profesor'), 
('Estudiante');

-- Insertar usuarios
INSERT INTO usuarios (id_rol, estado, nombres, apellidos, tipo_documento, numero_documento, correo, contraseña, sexo, telefono, direccion, fecha_nacimiento, fecha_creacion) 
VALUES 
(1, 1, 'Admin', 'Principal', 'Cédula', '12345678', 'admin@example.com', 'clave123', 'Masculino', '123456789', 'Calle Principal 123', '1980-01-01', CURDATE()),
(2, 1, 'Carlos', 'Martínez', 'Cédula', '23456789', 'profesor1@example.com', 'clave123', 'Masculino', '987654321', 'Calle Secundaria 456', '1990-02-15', CURDATE()),
(3, 1, 'Laura', 'Gómez', 'Cédula', '34567890', 'estudiante1@example.com', 'clave123', 'Femenino', '654987321', 'Calle Tercera 789', '2000-06-10', CURDATE());

-- Insertar clases
INSERT INTO clases (nombre, tipo_clase, duracion, generos, niveles, fecha) 
VALUES 
('Salsa Básica', 'Presencial', 60, 'Salsa', 'Básico', '2024-12-05'),
('Bachata Intermedia', 'Virtual', 90, 'Bachata', 'Intermedio', '2024-12-10'),
('Hip Hop Avanzado', 'Presencial', 120, 'Hip Hop', 'Avanzado', '2024-12-15');

-- Inscribir usuarios en clases
INSERT INTO clases_usuarios (id_clase, id_usuario) 
VALUES 
(1, 3), -- Laura en Salsa Básica
(2, 3); -- Laura en Bachata Intermedia

-- Registrar asistencias
CALL sp_registrar_asistencia(1, 3, TRUE); -- Laura asistió a Salsa Básica
CALL sp_registrar_asistencia(2, 3, FALSE); -- Laura no asistió a Bachata Intermedia

-- Calificar estudiantes
CALL sp_calificar_estudiante(1, 3, 85); -- Laura obtiene 85 en Salsa Básica
CALL sp_calificar_estudiante(2, 3, 90); -- Laura obtiene 90 en Bachata Intermedia

-- Enviar notificaciones
CALL sp_enviar_notificacion('Recuerda tu clase de Salsa Básica', NOW(), 3);
CALL sp_enviar_notificacion('No olvides practicar para Bachata Intermedia', NOW(), 3);

-- Crear eventos
INSERT INTO eventos (nombre, tipo_evento, fecha) 
VALUES 
('Campeonato de Salsa', 'Competencia', '2025-01-15'),
('Taller de Hip Hop', 'Taller', '2025-02-20');

-- Inscribir usuarios en eventos
INSERT INTO eventos_usuarios (id_evento, id_usuario) 
VALUES 
(1, 3), -- Laura en el Campeonato de Salsa
(2, 3); -- Laura en el Taller de Hip Hop




