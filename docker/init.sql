
USE escaperoom;

-- Tabla Escape Room
CREATE TABLE IF NOT EXISTS escape_room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla Sala
CREATE TABLE IF NOT EXISTS room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    theme VARCHAR(50),
    level INT,
    price DECIMAL(10,2) DEFAULT 0.0,
    escape_room_id INT DEFAULT NULL,
    FOREIGN KEY (escape_room_id) REFERENCES escape_room(id)
);

-- Tabla Pista
CREATE TABLE IF NOT EXISTS clue (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (100),
    theme VARCHAR(50),
    price DECIMAL(10,2) DEFAULT 0.0,
    description VARCHAR(500),
    room_id INT DEFAULT NULL,
    FOREIGN KEY (room_id) REFERENCES room(id)
);

-- Tabla Objeto Decoración
CREATE TABLE IF NOT EXISTS decoration_object (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    material VARCHAR(50),
    quantity INT DEFAULT 1,
    price DECIMAL(10,2) DEFAULT 0.0,
    room_id INT,
    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
);

-- Tabla de asignación de objectos a salas
CREATE TABLE IF NOT EXISTS room_objects (
    room_id INT NOT NULL,
    decoration_object_id INT NOT NULL,
    quantity INT,
    FOREIGN KEY (room_id) REFERENCES room(id),
    FOREIGN KEY (decoration_object_id) REFERENCES decoration_object(id)
);
-- Tabla cliente
CREATE TABLE IF NOT EXISTS client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100),
    email VARCHAR(50),
    phone VARCHAR(15)
);

-- Tabla Ticket
CREATE TABLE IF NOT EXISTS ticket (
    id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    room_id INT,
    total_price DECIMAL(10,2) DEFAULT 0.0,
    FOREIGN KEY (room_id) REFERENCES room(id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);

-- Insertar datos para provar
-- Insertar datos de prueba para escape_room
INSERT INTO escape_room (name) VALUES
('El Misterio del Faraón'),
('Laboratorio Secreto'),
('La Mansión Encantada'),
('Misión Espacial Orion');

-- Insertar salas para cada escape room
INSERT INTO room (name, theme, level, price, escape_room_id) VALUES
-- Salas para "El Misterio del Faraón"
('Templo de Anubis', 'Egipcio', 3, 25.00, 1),
('Cámara del Sarcófago', 'Egipcio', 4, 30.00, 1),
('Pasadizo Secreto', 'Aventura', 2, 20.00, 1),

-- Salas para "Laboratorio Secreto"
('Sala de Experimentos', 'Ciencia', 4, 28.00, 2),
('Archivo Confidencial', 'Misterio', 3, 22.00, 2),

-- Salas para "La Mansión Encantada"
('Sala del Espejo', 'Terror', 5, 35.00, 3),
('Biblioteca Oscura', 'Misterio', 3, 24.00, 3),

-- Salas para "Misión Espacial Orion"
('Puente de Mando', 'Futurista', 4, 32.00, 4),
('Cámara de Criogenia', 'Ciencia Ficción', 5, 38.00, 4);

-- Insertar objetos de decoración
INSERT INTO decoration_object (name, material, quantity, price) VALUES
-- Objetos egipcios
('Estatua de Anubis', 'Madera', 5, 150.00),
('Sarcófago Dorado', 'Madera y oro', 2, 500.00),
('Papiro Antiguo', 'Papel', 10, 25.00),
('Lámpara de Aceite', 'Arcilla', 8, 45.00),

-- Objetos de laboratorio
('Microscopio Vintage', 'Metal y vidrio', 3, 280.00),
('Tubo de Ensayo', 'Vidrio', 20, 8.00),
('Pizarra Científica', 'Madera', 4, 120.00),
('Bata de Laboratorio', 'Algodón', 6, 35.00),

-- Objetos de mansión encantada
('Espejo Antiguo', 'Madera y vidrio', 2, 320.00),
('Candelabro', 'Metal', 5, 95.00),
('Libro de Hechizos', 'Cuero', 3, 65.00),
('Alfombra Persa', 'Lana', 4, 180.00),

-- Objetos espaciales
('Panel de Control', 'Metal y LEDs', 3, 420.00),
('Traje Espacial', 'Tela especial', 4, 380.00),
('Consola de Navegación', 'Plástico y metal', 2, 290.00),
('Capsula Criogénica', 'Acero', 1, 650.00);

-- Asignar objetos a las salas (tabla room_objects)
INSERT INTO room_objects (room_id, decoration_object_id, quantity) VALUES
-- Para Templo de Anubis (room_id 1)
(1, 1, 2),  -- 2 Estatuas de Anubis
(1, 3, 5),  -- 5 Papiros Antiguos
(1, 4, 3),  -- 3 Lámparas de Aceite

-- Para Cámara del Sarcófago (room_id 2)
(2, 2, 1),  -- 1 Sarcófago Dorado
(2, 1, 1),  -- 1 Estatua de Anubis

-- Para Sala de Experimentos (room_id 4)
(4, 5, 2),  -- 2 Microscopios
(4, 6, 12), -- 12 Tubos de Ensayo
(4, 7, 1),  -- 1 Pizarra Científica

-- Para Sala del Espejo (room_id 6)
(6, 9, 1),  -- 1 Espejo Antiguo
(6, 10, 2), -- 2 Candelabros
(6, 11, 1), -- 1 Libro de Hechizos

-- Para Puente de Mando (room_id 8)
(8, 13, 1), -- 1 Panel de Control
(8, 14, 2), -- 2 Trajes Espaciales
(8, 15, 1), -- 1 Consola de Navegación

-- Para Cámara de Criogenia (room_id 9)
(9, 16, 1), -- 1 Cápsula Criogénica
(9, 14, 1); -- 1 Traje Espacial

-- Insertar pistas para las salas
INSERT INTO clue (name, theme, price, description, room_id) VALUES
-- Pistas para salas egipcias
('Jeroglífico de Anubis', 'Egipcio', 5.00, 'El orden de los símbolos indica la combinación del candado', 1),
('Mapa del Templo', 'Aventura', 7.00, 'Muestra pasadizos secretos y cámaras ocultas', 1),
('Inscripción del Sarcófago', 'Egipcio', 8.00, 'Contiene pistas sobre la ubicación de la llave maestra', 2),

-- Pistas para laboratorio
('Diario del Científico', 'Ciencia', 6.00, 'Describe los experimentos fallidos y sus soluciones', 4),
('Fórmula Química', 'Misterio', 9.00, 'La secuencia correcta de elementos abre la caja fuerte', 4),
('Código de Acceso', 'Tecnología', 4.00, 'Encontrado detrás de un cuadro en el archivo', 5),

-- Pistas para mansión encantada
('Carta Antigua', 'Terror', 7.00, 'Relata la historia del fantasma que habita la mansión', 6),
('Reflejo del Espejo', 'Misterio', 10.00, 'Muestra objetos que no están en la sala real', 6),
('Poema Enigmático', 'Literatura', 6.00, 'Cada estrofa revela una pista diferente', 7),

-- Pistas para misión espacial
('Manual de Navegación', 'Futurista', 8.00, 'Contiene coordenadas estelares importantes', 8),
('Registro de la Tripulación', 'Ciencia Ficción', 5.00, 'Menciona habilidades específicas de cada miembro', 8),
('Protocolo de Emergencia', 'Tecnología', 12.00, 'Describe cómo reactivar los sistemas criogénicos', 9);

-- Insertar clientes
INSERT INTO client (user_name, email, phone) VALUES
('Ana García', 'ana.garcia@email.com', '+34 612 345 678'),
('Carlos Martínez', 'carlos.m@email.com', '+34 623 456 789'),
('Laura Chen', 'laura.chen@email.com', '+34 634 567 890'),
('Miguel Rodríguez', 'm.rodriguez@email.com', '+34 645 678 901'),
('Sofia Patel', 'sofia.patel@email.com', '+34 656 789 012'),
('David Kim', 'david.kim@email.com', '+34 667 890 123');

-- Insertar tickets
INSERT INTO ticket (client_id, room_id, total_price) VALUES
(1, 1, 25.00),   -- Ana en Templo de Anubis
(2, 4, 28.00),   -- Carlos en Sala de Experimentos
(3, 6, 35.00),   -- Laura en Sala del Espejo
(1, 8, 32.00),   -- Ana en Puente de Mando
(4, 2, 30.00),   -- Miguel en Cámara del Sarcófago
(5, 9, 38.00),   -- Sofia en Cámara de Criogenia
(6, 4, 28.00);   -- David en Sala de Experimentos





