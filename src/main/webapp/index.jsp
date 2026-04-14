<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EoApp - Inicio</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<nav class="navbar">
    <a href="index.jsp" class="logo">Eo<span>App</span></a>
    <ul class="nav-links">
        <li><a href="index.jsp" class="activo">Inicio</a></li>
        <li><a href="gastos">Gastos</a></li>
        <li><a href="ingresos">Ingresos</a></li>
        <li><a href="balance">Balance</a></li>
    </ul>
</nav>

<div class="container">
    <div class="page-header">
        <h1>Bienvenido a EoApp 👋</h1>
        <p>Tu app de gestión de gastos personales</p>
    </div>

    <div class="resumen-grid">
        <div class="resumen-card verde">
            <div class="etiqueta">💰 Ingresos</div>
            <div class="valor">$ --</div>
            <div class="etiqueta">Total registrado</div>
        </div>
        <div class="resumen-card rojo">
            <div class="etiqueta">💸 Gastos</div>
            <div class="valor">$ --</div>
            <div class="etiqueta">Total registrado</div>
        </div>
        <div class="resumen-card azul">
            <div class="etiqueta">📊 Balance</div>
            <div class="valor">$ --</div>
            <div class="etiqueta">Disponible</div>
        </div>
    </div>

    <div class="card">
        <h2>¿Qué deseas hacer?</h2>
        <div style="display:flex; gap:1rem; flex-wrap:wrap; margin-top:.5rem;">
            <a href="gastos" class="btn btn-primary">➕ Registrar Gasto</a>
            <a href="ingresos" class="btn btn-primary" style="background:#3498db;">💵 Registrar Ingreso</a>
            <a href="balance" class="btn btn-primary" style="background:#9b59b6;">📊 Ver Balance</a>
        </div>
    </div>

    <div class="card">
        <h2>Acerca de EoApp</h2>
        <p style="color:var(--gris); line-height:1.7;">
            EoApp es una aplicación web para el control de gastos e ingresos personales.
            Permite registrar, listar y analizar tu situación financiera mensual.
            Desarrollada con <strong>Java Servlets</strong>, <strong>JSP</strong> y <strong>MySQL</strong>
            como parte de la evidencia <strong>GA7-220501096-AA2-EV02</strong>.
        </p>
    </div>
</div>

<footer>
    EoApp &copy; 2024 &mdash; GA7-220501096-AA2-EV02 | SENA Tecnología en Análisis y Desarrollo de Software
</footer>

</body>
</html>
