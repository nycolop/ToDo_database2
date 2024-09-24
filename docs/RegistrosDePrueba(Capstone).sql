use proyectoToDo;

INSERT INTO Usuario (nombre, apellido, contraseña) VALUES
('Juan', 'Pérez', 'Password123!'),
('Ana', 'Gómez', 'SecurePass456$'),
('Carlos', 'López', 'MySecret789@'),
('María', 'Rodríguez', 'PassWord321#'),
('Luis', 'Martínez', 'Martinez2024&'),
('Sofía', 'Jiménez', 'SofiaSecure!'),
('Pedro', 'Fernández', 'PedroPass#123'),
('Lucía', 'Vargas', 'LuciaV#890'),
('Miguel', 'Ortega', 'Miguel@1234'),
('Elena', 'Ruiz', 'ElenaPassword!');

INSERT INTO Tarea (nombre, estado, descripcion, prioridad, fecha_inicio, fecha_fin_estimado, fecha_final) VALUES
('Revisar documentación', 'Pendiente', 'Revisar la documentación de la nueva funcionalidad.', 'Alta', '2024-09-24 09:00:00', '2024-09-28 17:00:00', NULL),
('Planificar sprint', 'Pendiente', 'Planificar las tareas del próximo sprint de desarrollo.', 'Alta', '2024-09-25 10:00:00', '2024-09-26 12:00:00', NULL),
('Actualizar servidor', 'Completado', 'Actualizar el servidor de producción a la última versión.', 'Media', '2024-09-22 15:00:00', '2024-09-22 17:00:00', '2024-09-22 16:30:00'),
('Diseñar interfaz', 'En Progreso', 'Diseñar la nueva interfaz de usuario para la aplicación.', 'Baja', '2024-09-20 14:00:00', '2024-10-01 17:00:00', NULL),
('Implementar login', 'Completado', 'Implementar funcionalidad de login en la aplicación.', 'Alta', '2024-09-18 08:00:00', '2024-09-20 18:00:00', '2024-09-19 13:45:00'),
('Pruebas unitarias', 'Pendiente', 'Crear y ejecutar pruebas unitarias para el módulo de usuario.', 'Media', '2024-09-23 11:00:00', '2024-09-27 17:00:00', NULL),
('Crear informe mensual', 'Completado', 'Crear informe mensual de actividades y rendimiento.', 'Baja', '2024-09-01 09:00:00', '2024-09-05 12:00:00', '2024-09-04 10:30:00'),
('Optimizar base de datos', 'Pendiente', 'Optimizar las consultas de la base de datos para mejorar el rendimiento.', 'Alta', '2024-09-26 09:00:00', '2024-10-01 18:00:00', NULL),
('Capacitación equipo', 'En Progreso', 'Capacitación sobre nuevas tecnologías al equipo de desarrollo.', 'Media', '2024-09-15 13:00:00', '2024-09-30 18:00:00', NULL),
('Desplegar aplicación', 'Pendiente', 'Desplegar la nueva versión de la aplicación en el entorno de producción.', 'Alta', '2024-09-27 09:00:00', '2024-09-27 18:00:00', NULL);

INSERT INTO Etiqueta (nombre, descripcion) VALUES
('Documentación', 'Relacionado con tareas de documentación.'),
('Planificación', 'Tareas relacionadas con la planificación de proyectos.'),
('Desarrollo', 'Tareas relacionadas con el desarrollo de software.'),
('Mantenimiento', 'Relacionado con mantenimiento de sistemas y aplicaciones.'),
('Diseño', 'Relacionado con el diseño de interfaces y experiencia de usuario.'),
('Pruebas', 'Tareas relacionadas con testing y aseguramiento de calidad.'),
('Capacitación', 'Actividades de capacitación y formación del equipo.'),
('Implementación', 'Tareas relacionadas con la implementación de nuevas funcionalidades.');

INSERT INTO Usuario_Tarea (usuario_id, tarea_id) VALUES
(1, 1),  -- Juan Pérez está asignado a la tarea "Revisar documentación"
(1, 4),  -- Juan Pérez está asignado a la tarea "Diseñar interfaz"
(2, 2),  -- Ana Gómez está asignada a la tarea "Planificar sprint"
(2, 3),  -- Ana Gómez está asignada a la tarea "Actualizar servidor"
(3, 5),  -- Carlos López está asignado a la tarea "Implementar login"
(3, 7),  -- Carlos López está asignado a la tarea "Crear informe mensual"
(4, 6),  -- María Rodríguez está asignada a la tarea "Pruebas unitarias"
(5, 8),  -- Luis Martínez está asignado a la tarea "Optimizar base de datos"
(6, 9),  -- Sofía Jiménez está asignada a la tarea "Capacitación equipo"
(7, 10), -- Pedro Fernández está asignado a la tarea "Desplegar aplicación"
(8, 2),  -- Lucía Vargas también está asignada a la tarea "Planificar sprint"
(9, 3),  -- Miguel Ortega también está asignado a la tarea "Actualizar servidor"
(10, 1); -- Elena Ruiz está asignada a la tarea "Revisar documentación"

INSERT INTO Tarea_Etiqueta (tarea_id, etiqueta_id) VALUES
(1, 1),  -- La tarea "Revisar documentación" tiene la etiqueta "Documentación"
(2, 2),  -- La tarea "Planificar sprint" tiene la etiqueta "Planificación"
(3, 4),  -- La tarea "Actualizar servidor" tiene la etiqueta "Mantenimiento"
(4, 5),  -- La tarea "Diseñar interfaz" tiene la etiqueta "Diseño"
(5, 3),  -- La tarea "Implementar login" tiene la etiqueta "Desarrollo"
(6, 6),  -- La tarea "Pruebas unitarias" tiene la etiqueta "Pruebas"
(7, 1),  -- La tarea "Crear informe mensual" tiene la etiqueta "Documentación"
(8, 4),  -- La tarea "Optimizar base de datos" tiene la etiqueta "Mantenimiento"
(9, 7),  -- La tarea "Capacitación equipo" tiene la etiqueta "Capacitación"
(10, 8), -- La tarea "Desplegar aplicación" tiene la etiqueta "Implementación"
(2, 3),  -- La tarea "Planificar sprint" también tiene la etiqueta "Desarrollo"
(3, 3);  -- La tarea "Actualizar servidor" también tiene la etiqueta "Desarrollo"
