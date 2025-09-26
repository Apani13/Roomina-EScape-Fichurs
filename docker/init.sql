
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