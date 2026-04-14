<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EoApp - Ingresos</title>
    <link rel="stylesheet" href="../css/estilos.css">
</head>
<body>

<nav class="navbar">
    <a href="../index.jsp" class="logo">Eo<span>App</span></a>
    <ul class="nav-links">
        <li><a href="../index.jsp">Inicio</a></li>
        <li><a href="../gastos">Gastos</a></li>
        <li><a href="../ingresos" class="activo">Ingresos</a></li>
        <li><a href="../balance">Balance</a></li>
    </ul>
</nav>

<div class="container">

    <div class="page-header">
        <h1>💰 Gestión de Ingresos</h1>
        <p>Registra tus fuentes de ingreso</p>
    </div>

    <%-- Mensaje de resultado --%>
    <c:if test="${not empty mensaje}">
        <div class="mensaje ${tipoMensaje}">
            ${mensaje}
        </div>
    </c:if>

    <%-- ===== FORMULARIO HTML - método POST ===== --%>
    <div class="card">
        <h2>➕ Nuevo Ingreso</h2>
        <form action="../ingresos" method="post">
            <div class="form-grid">

                <div class="form-group">
                    <label for="descripcion">Descripción</label>
                    <input type="text" id="descripcion" name="descripcion"
                           placeholder="Ej: Salario mes de junio" required maxlength="150">
                </div>

                <div class="form-group">
                    <label for="fuente">Fuente</label>
                    <select id="fuente" name="fuente" required>
                        <option value="">-- Seleccione --</option>
                        <option value="Salario">💼 Salario</option>
                        <option value="Freelance">💻 Freelance</option>
                        <option value="Negocio propio">🏪 Negocio propio</option>
                        <option value="Inversiones">📈 Inversiones</option>
                        <option value="Arriendo">🏠 Arriendo</option>
                        <option value="Regalo">🎁 Regalo</option>
                        <option value="Otro">📌 Otro</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="monto">Monto ($)</label>
                    <input type="number" id="monto" name="monto"
                           placeholder="0.00" step="0.01" min="0.01" required>
                </div>

                <div class="form-group">
                    <label for="fecha">Fecha</label>
                    <input type="date" id="fecha" name="fecha" required>
                </div>

            </div>
            <div class="btn-area">
                <button type="submit" class="btn btn-primary" style="background:#3498db;">Guardar Ingreso</button>
                <button type="reset"  class="btn" style="background:transparent; border:1px solid var(--borde); color:var(--gris); margin-left:.5rem;">Limpiar</button>
            </div>
        </form>
    </div>

    <%-- ===== TABLA DE INGRESOS ===== --%>
    <div class="card">
        <h2>📋 Historial de Ingresos
            <span style="float:right; color:var(--gris); font-size:.85rem; font-weight:400;">
                Total: <strong style="color:var(--verde);">
                    $<fmt:formatNumber value="${totalIngresos}" pattern="#,##0.00"/>
                </strong>
            </span>
        </h2>

        <div class="tabla-container">
            <c:choose>
                <c:when test="${empty listaIngresos}">
                    <p style="color:var(--gris); text-align:center; padding:2rem;">
                        No hay ingresos registrados aún. ¡Agrega tu primer ingreso!
                    </p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Descripción</th>
                                <th>Fuente</th>
                                <th>Monto</th>
                                <th>Fecha</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ingreso" items="${listaIngresos}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${ingreso.descripcion}</td>
                                    <td>
                                        <span class="badge badge-alimentacion">${ingreso.fuente}</span>
                                    </td>
                                    <td style="color:var(--verde);">
                                        $<fmt:formatNumber value="${ingreso.monto}" pattern="#,##0.00"/>
                                    </td>
                                    <td>${ingreso.fecha}</td>
                                    <td>
                                        <a href="../ingresos?accion=eliminar&id=${ingreso.id}"
                                           class="btn btn-danger"
                                           onclick="return confirm('¿Eliminar este ingreso?')">
                                            Eliminar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>

<footer>
    EoApp &copy; 2024 &mdash; GA7-220501096-AA2-EV02 | SENA ADSO
</footer>

<script>
    document.getElementById('fecha').valueAsDate = new Date();
</script>

</body>
</html>
