
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
CREATE TABLE IF NOT EXISTS item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    material VARCHAR(50),
    quantity INT DEFAULT 1,
    price DECIMAL(10,2) DEFAULT 0.0
    /*room_id INT,
    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE*/
);

-- Tabla de asignación de objetos a salas
CREATE TABLE IF NOT EXISTS room_item (
    room_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT,
    FOREIGN KEY (room_id) REFERENCES room(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);
-- Tabla cliente
CREATE TABLE IF NOT EXISTS client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(100),
    email VARCHAR(50),
    phone VARCHAR(15),
    accepts_notifications TINYINT(1) NOT NULL DEFAULT 0
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