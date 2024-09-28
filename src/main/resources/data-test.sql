--DATOS DE PRUEBA PARA LA TABLA VETERINARIO
INSERT INTO Veterinario (nombre, apellido, especialidad, correo, contrasena, rol) VALUES
                                                             ('Carlos Roberto', 'Pérez Gomez', 'Cirugía Veterinaria', 'carlosP@example.com', 'password123', 'USER'),
                                                             ('Ana Lucia', 'López Arteaga', 'Dermatología Veterinaria', 'anaL@example.com', 'password789', 'USER'),
                                                             ('Juan Luis', 'Martínez Guerra', 'Odontología Veterinaria', 'juanM@example.com', 'password456', 'USER'),
                                                             ('Celia', 'García Mercedes', 'Medicina Interna Veterinaria', 'celiaG@example.com', 'password258', 'USER')
    ON CONFLICT DO NOTHING;

--DATOS DE PRUEBA PARA LA TABLA DUEÑO
INSERT INTO Dueno (nombre, apellido, telefono, direccion, correo, contrasena, rol) VALUES
                                                                          ('Pedro Sebastian', 'Ramírez Flores', '987654321', 'Av. Principal 123', 'pedroR@example.com', 'password753', 'USER'),
                                                                          ('Ana Rosa', 'Fernández Ruiz', '912345678', 'Calle Secundaria 456', 'anaF@example.com', 'password159', 'USER' ),
                                                                          ('Luis Angel', 'González Prado', '956789123', 'Paseo de la República 789', 'luisA@example.com', 'password842', 'USER' ),
                                                                          ('Maria Isabel', 'Pérez Gomez', '934567890', 'Calle Los Pinos 321', 'mariaP@example.com', 'password941', 'USER' )
    ON CONFLICT DO NOTHING;

--DATOS DE PRUEBA PARA LA TABLA MASCOTA
INSERT INTO Mascota (nombre, especie, raza, edad, dueno_id) VALUES
                                                                ('Bobby', 'Perro', 'Labrador', 3, 1),
                                                                ('Gardfield', 'Gato', 'Siames', 2, 2),
                                                                ('Rex', 'Perro', 'Pastor Alemán', 5, 3),
                                                                ('Nina', 'Gato', 'Persa', 4, 4)
    ON CONFLICT DO NOTHING;

--DATOS DE PRUEBA PARA LA TABLA HORARIOS DE DISPONIBILIDAD
INSERT INTO Horario_Disponibilidad (hora_inicio, hora_fin, veterinario_id) VALUES
                                                                               ('2024-09-30 09:00:00', '2024-09-30 12:00:00', 1),
                                                                             ('2024-09-30 14:00:00', '2024-09-30 17:00:00', 2),
                                                                              ('2024-09-30 08:00:00', '2024-09-30 11:00:00', 3),
                                                                              ('2024-09-30 13:00:00', '2024-09-30 16:00:00', 4)
    ON CONFLICT DO NOTHING;

--DATOS DE PRUEBA PARA LA TABLA CITA
INSERT INTO Cita (fecha, descripcion, estado, dueno_id, veterinario_id) VALUES
                                                                              ('2024-09-30 10:00:00', 'Consulta general para el perro', 'PENDIENTE', 1, 1),
                                                                              ('2024-09-30 11:30:00', 'Revisión de vacunas', 'PENDIENTE', 2, 2),
                                                                              ('2024-09-30 15:00:00', 'Chequeo anual del gato', 'PENDIENTE', 3, 3),
                                                                              ('2024-09-30 16:30:00', 'Consulta sobre alergias', 'PENDIENTE', 4, 4)
    ON CONFLICT DO NOTHING;

