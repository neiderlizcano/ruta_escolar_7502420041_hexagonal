DROP DATABASE IF EXISTS ruta_escolar_hex;

CREATE DATABASE ruta_escolar_hex
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE ruta_escolar_hex;

CREATE TABLE conductor (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  nombres          VARCHAR(80)  NOT NULL,
  apellidos        VARCHAR(80)  NOT NULL,
  documento        VARCHAR(20)  NOT NULL,
  nro_licencia     VARCHAR(32)  NOT NULL,
  categoria_lic    VARCHAR(10)  NOT NULL,
  telefono         VARCHAR(20),
  estado           ENUM('ACTIVO','INACTIVO','SUSPENDIDO') NOT NULL DEFAULT 'ACTIVO',
  fecha_creacion   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fecha_actualizacion TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT uq_conductor_documento UNIQUE (documento),
  CONSTRAINT uq_conductor_licencia  UNIQUE (nro_licencia)
);

CREATE TABLE asistente (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  nombres          VARCHAR(80)  NOT NULL,
  apellidos        VARCHAR(80)  NOT NULL,
  documento        VARCHAR(20)  NOT NULL,
  telefono         VARCHAR(20),
  estado           ENUM('ACTIVO','INACTIVO','SUSPENDIDO') NOT NULL DEFAULT 'ACTIVO',
  fecha_creacion   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fecha_actualizacion TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT uq_asistente_documento UNIQUE (documento)
);

CREATE TABLE IF NOT EXISTS bus (
  id                  INT AUTO_INCREMENT PRIMARY KEY,
  placa               VARCHAR(10) NOT NULL,
  capacidad           INT         NOT NULL,
  estado              ENUM('ACTIVO','INACTIVO','SUSPENDIDO') NOT NULL DEFAULT 'ACTIVO',
  fecha_creacion      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fecha_actualizacion TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT uq_bus_placa UNIQUE (placa)
);

INSERT INTO conductor (nombres, apellidos, documento, nro_licencia, categoria_lic, telefono, estado)
VALUES
('Carlos', 'Gomez', '1001', 'LIC-001', 'C2', '3001111111', 'ACTIVO'),
('Ana',   'Diaz',  '1002', 'LIC-002', 'C1', '3002222222', 'INACTIVO');

INSERT INTO asistente (nombres, apellidos, documento, telefono, estado)
VALUES
('Laura',  'Perez', '2001', '3003333333', 'ACTIVO'),
('Miguel', 'Lopez', '2002', '3004444444', 'SUSPENDIDO');

INSERT INTO bus (placa, capacidad, estado)
VALUES
('ABC123', 40, 'ACTIVO'),
('XYZ789', 30, 'INACTIVO');