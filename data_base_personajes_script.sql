-- Script de creación de Base de Datos para API de Personajes
-- Este script muestra la estructura utilizada en la aplicación

-- --------------------------------------------------------
-- Crear la base de datos (si no existe)
-- --------------------------------------------------------
CREATE DATABASE IF NOT EXISTS personajes_db;
USE personajes_db;

-- --------------------------------------------------------
-- Estructura de la tabla 'personajes'
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS personajes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    identificacion VARCHAR(100) UNIQUE NOT NULL,
    imagen VARCHAR(500) NULL,
    rol VARCHAR(100) NULL,
    descripcion TEXT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------
-- Datos iniciales de ejemplo (opcionales)
-- --------------------------------------------------------
INSERT INTO personajes (nombre, identificacion, rol, descripcion) VALUES
('Harry Potter', 'HP001', 'Estudiante de Gryffindor', 'El niño que sobrevivió a Lord Voldemort'),
('Hermione Granger', 'HG002', 'Estudiante de Gryffindor', 'La bruja más inteligente de su generación');

-- --------------------------------------------------------
-- Verificación
-- --------------------------------------------------------
SELECT 'Estructura de la base de datos' AS Informacion;
DESCRIBE personajes;