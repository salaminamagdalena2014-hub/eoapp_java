-- =============================================
--  EoApp - Script de Base de Datos MySQL
--  GA7-220501096-AA2-EV02
--  Ejecutar en MySQL antes de correr el proyecto
-- =============================================

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS eoapp_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE eoapp_db;

-- ===== Tabla de Gastos =====
CREATE TABLE IF NOT EXISTS gastos (
    id           INT          AUTO_INCREMENT PRIMARY KEY,
    descripcion  VARCHAR(150) NOT NULL,
    categoria    VARCHAR(50)  NOT NULL,
    monto        DECIMAL(10,2) NOT NULL,
    fecha        DATE         NOT NULL,
    metodo_pago  VARCHAR(50)  NOT NULL,
    creado_en    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- ===== Tabla de Ingresos =====
CREATE TABLE IF NOT EXISTS ingresos (
    id           INT          AUTO_INCREMENT PRIMARY KEY,
    descripcion  VARCHAR(150) NOT NULL,
    fuente       VARCHAR(50)  NOT NULL,
    monto        DECIMAL(10,2) NOT NULL,
    fecha        DATE         NOT NULL,
    creado_en    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- ===== Datos de prueba - Gastos =====
INSERT INTO gastos (descripcion, categoria, monto, fecha, metodo_pago) VALUES
('Mercado semanal',        'Alimentación',   85000.00, '2024-06-01', 'Tarjeta débito'),
('Recarga Transmilenio',   'Transporte',     50000.00, '2024-06-02', 'Efectivo'),
('Consulta médica',        'Salud',          60000.00, '2024-06-05', 'Efectivo'),
('Cursos en línea SENA',   'Educación',      0.00,     '2024-06-06', 'Efectivo'),
('Factura de internet',    'Servicios',      79900.00, '2024-06-07', 'Transferencia'),
('Cine + comida',          'Entretenimiento',45000.00, '2024-06-08', 'Nequi'),
('Ropa deportiva',         'Ropa',           120000.00,'2024-06-10', 'Tarjeta crédito'),
('Supermercado',           'Alimentación',   132000.00,'2024-06-15', 'Tarjeta débito');

-- ===== Datos de prueba - Ingresos =====
INSERT INTO ingresos (descripcion, fuente, monto, fecha) VALUES
('Salario mes de junio',    'Salario',        1800000.00, '2024-06-01'),
('Proyecto freelance web',  'Freelance',      350000.00,  '2024-06-10'),
('Venta de artículos',      'Negocio propio', 95000.00,   '2024-06-15');

-- ===== Verificación =====
SELECT 'Gastos creados:'   AS info, COUNT(*) AS total FROM gastos;
SELECT 'Ingresos creados:' AS info, COUNT(*) AS total FROM ingresos;
