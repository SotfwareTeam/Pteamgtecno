
-- Triggers

-- Trigger para verificar existencia de usuario al enviar notificación
DELIMITER //
CREATE TRIGGER trg_envio_notificaciones
BEFORE INSERT ON notificaciones
FOR EACH ROW
BEGIN
    DECLARE usuario_existe INT;
    SET usuario_existe = (SELECT COUNT(*) FROM usuarios WHERE id_usuario = NEW.id_usuario);
    IF usuario_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Usuario no registrado';
    END IF;
END //
DELIMITER ;

-- Trigger para enviar notificación si un usuario no asiste a clase
DELIMITER //
CREATE TRIGGER trg_verificar_asistencia
AFTER INSERT ON reporte_asistencias
FOR EACH ROW
BEGIN
    IF NEW.asistencia = FALSE THEN
        CALL sp_enviar_notificacion('No asistió a la clase', NOW(), NEW.id_usuario);
    END IF;
END //
DELIMITER ;

-- Trigger para actualizar el promedio después de una calificación
DELIMITER //
CREATE TRIGGER trg_actualizar_promedio_calificacion
AFTER INSERT ON reporte_calificaciones
FOR EACH ROW
BEGIN
    DECLARE nuevo_promedio FLOAT;
    
    -- Calcula el nuevo promedio de calificaciones para el usuario en la clase específica
    SELECT AVG(calificacion) INTO nuevo_promedio
    FROM reporte_calificaciones
    WHERE id_clase = NEW.id_clase AND id_usuario = NEW.id_usuario;
    
    -- Actualiza el promedio en el registro insertado
    UPDATE reporte_calificaciones
    SET promedio = nuevo_promedio
    WHERE id_reporte_calificacion = NEW.id_reporte_calificacion;
END //
DELIMITER ;