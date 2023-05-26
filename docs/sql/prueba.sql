
CREATE DATABASE inventarios_bd;

USE inventarios_bd;

#NOTA: Los registros se deben crear desde la app, usa UUID generado por la app
CREATE TABLE usuarios (
  id BINARY(16) NOT NULL,
  nombre VARCHAR(75) NOT NULL,
  apellido VARCHAR(75) NOT NULL,
  telefono VARCHAR(15),
  email VARCHAR(75) NOT NULL,
  password VARCHAR(250) NOT NULL,
  authority VARCHAR(255) NOT NULL,
  enabled TINYINT(1) DEFAULT 1,
  creationDateTime DATETIME,
  lastModified DATETIME,
  PRIMARY KEY (id),
  UNIQUE INDEX usuarios_unique (email)
);

CREATE TABLE productos (
  id BINARY(16) NOT NULL,
  codigo VARCHAR(20) NOT NULL,
  nombre VARCHAR(150) NOT NULL,
  cantidad DECIMAL(30, 2) NOT NULL,
  valorCompra DECIMAL(30, 2) NOT NULL,
  valorVenta DECIMAL(30, 2) NOT NULL,
  disponible TINYINT(1) NOT NULL,
  fechaCreacion DATETIME NOT NULL,
  ultimaActualizacion DATETIME,
  PRIMARY KEY (id),
  UNIQUE INDEX productos_unique (codigo)
);

CREATE TABLE Token (
  id INT AUTO_INCREMENT NOT NULL,
  token VARCHAR(255) NOT NULL,
  tokenType VARCHAR(50) NOT NULL,
  revoked TINYINT(1) DEFAULT 0,
  expired TINYINT(1) DEFAULT 0,
  idUsuario BINARY(16),
  PRIMARY KEY (id),
  UNIQUE INDEX tokens_unique (token),
  FOREIGN KEY (idUsuario) REFERENCES usuarios (id)
);

#NOTA: Los registros se deben crear desde la app, usa UUID generado por la app
INSERT INTO productos (codigo, nombre, cantidad, valorCompra, valorVenta, disponible, fechaCreacion)
VALUES 
    ('ABC123', 'Producto 1', 15, 50.25, 75.99, 1, NOW()),
    ('DEF123', 'Producto 2', 10, 40.20, 80.99, 1, NOW()),
    ('GHI123', 'Producto 3', 20, 20.25, 50.99, 1, NOW()),
    ('JKL123', 'Producto 4', 2, 100.25, 250.99, 1, NOW()),
    ('MNO123', 'Producto 5', 10.5, 50.25, 75.99, 1, NOW()),
    ('PQR123', 'Producto 6', 10.5, 50.25, 75.99, 1, NOW());




