

-- Procedimientos almacenados

-- Procedimiento para calificar a un estudiante
DELIMITER //
CREATE PROCEDURE sp_calificar_estudiante(
    IN clase_id INT,
    IN usuario_id INT,
    IN calificacion INT
)
BEGIN
    DECLARE promedio_cal FLOAT;
    SET promedio_cal = (SELECT AVG(calificacion) FROM reporte_calificaciones WHERE id_usuario = usuario_id);
    INSERT INTO reporte_calificaciones (id_clase, id_usuario, calificacion, promedio, fecha)
    VALUES (clase_id, usuario_id, calificacion, promedio_cal, NOW());
END //
DELIMITER ;

-- Procedimiento para enviar notificación
DELIMITER //
CREATE PROCEDURE sp_enviar_notificacion(
    IN mensaje TEXT,
    IN fecha_envio DATETIME,
    IN usuario_id INT
)
BEGIN
    INSERT INTO notificaciones (mensaje, fecha_envio, id_usuario) 
    VALUES (mensaje, fecha_envio, usuario_id);
END //
DELIMITER ;

-- Procedimiento para registrar asistencia
DELIMITER //
CREATE PROCEDURE sp_registrar_asistencia(
    IN clase_id INT,
    IN usuario_id INT,
    IN asistio BOOLEAN
)
BEGIN
    INSERT INTO reporte_asistencias (id_clase, id_usuario, asistencia)
    VALUES (clase_id, usuario_id, asistio);
END //
DELIMITER ;