

-- Funciones

-- Función para obtener clases de un profesor en un día específico
DELIMITER //
CREATE FUNCTION fn_profesor_clases_dia(profesor_id INT, fecha DATE) 
RETURNS VARCHAR(255)
DETERMINISTIC
BEGIN
    DECLARE clases VARCHAR(255);
    
    SELECT GROUP_CONCAT(nombre SEPARATOR ', ') INTO clases
    FROM clases
    WHERE id_clase IN (SELECT id_clase FROM clases_usuarios WHERE id_usuario = profesor_id) 
      AND fecha = fecha;
    
    RETURN clases;
END //
DELIMITER ;

-- Función para obtener el ranking de un estudiante basado en su promedio
DELIMITER //
CREATE FUNCTION fn_estudiante_ranking(usuario_id INT) 
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE ranking INT;
    DECLARE promedio_usuario FLOAT;
    
    SELECT AVG(calificacion) INTO promedio_usuario
    FROM reporte_calificaciones
    WHERE id_usuario = usuario_id;
    
    SELECT COUNT(DISTINCT promedio) + 1 INTO ranking
    FROM reporte_calificaciones
    WHERE promedio > promedio_usuario;
    
    RETURN ranking;
END //
DELIMITER ;

-- Función para calcular el promedio de calificaciones de una clase
DELIMITER //
CREATE FUNCTION fn_calificaciones_promedio_clase(clase_id INT) 
RETURNS FLOAT
DETERMINISTIC
BEGIN
    DECLARE promedio FLOAT;
    
    SELECT AVG(calificacion) INTO promedio
    FROM reporte_calificaciones
    WHERE id_clase = clase_id;
    
    RETURN promedio;
END //
DELIMITER ;

-- Función para calcular el porcentaje de ocupación de una clase
DELIMITER //
CREATE FUNCTION fn_clase_ocupacion(clase_id INT, capacidad INT) 
RETURNS FLOAT
DETERMINISTIC
BEGIN
    DECLARE ocupacion FLOAT;
    
    SET ocupacion = (SELECT COUNT(*) FROM clases_usuarios WHERE id_clase = clase_id);
    
    RETURN (ocupacion / capacidad) * 100;
END //
DELIMITER ;

-- Función para contar asistencias de un estudiante en un rango de fechas
DELIMITER //
CREATE FUNCTION fn_estudiante_asistencias(usuario_id INT, fecha_inicio DATE, fecha_fin DATE) 
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total_asistencias INT;
    
    SELECT COUNT(*) INTO total_asistencias
    FROM reporte_asistencias
    WHERE id_usuario = usuario_id
      AND asistencia = TRUE
      AND fecha BETWEEN fecha_inicio AND fecha_fin;
    
    RETURN total_asistencias;
END //
DELIMITER ;