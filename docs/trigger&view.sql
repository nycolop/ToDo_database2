use proyectoToDo;
DELIMITER $$
CREATE TRIGGER actualizar_estado_tarea
BEFORE UPDATE ON Tarea
FOR EACH ROW
BEGIN
    IF NEW.fecha_inicio <= NOW() AND NEW.estado NOT IN ('completada', 'terminada') THEN
        SET NEW.estado = 'en progreso';
    END IF;

    IF NEW.fecha_fin_estimado < NOW() AND NEW.estado NOT IN ('completada', 'terminada') THEN
        SET NEW.estado = 'atrasada';
    END IF;
END $$

DELIMITER ;

create view vista_usuarios_tareas as
select 
    u.id as usuario_id,
    CONCAT(u.nombre, " ", u.apellido) as nombre_completo,
    t.id as tarea_id,
    t.nombre as nombre_tarea,
    t.estado,
    t.fecha_inicio,
    t.fecha_fin_estimado
from 
    Usuario u
join 
    Usuario_Tarea ut on u.id = ut.usuario_id
join 
    Tarea t on t.id = ut.tarea_id;
