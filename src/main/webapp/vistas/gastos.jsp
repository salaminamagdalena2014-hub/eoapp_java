<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EoApp - Gastos</title>
    <link rel="stylesheet" href="../css/estilos.css">
</head>
<body>

<nav class="navbar">
    <a href="../index.jsp" class="logo">Eo<span>App</span></a>
    <ul class="nav-links">
        <li><a href="../index.jsp">Inicio</a></li>
        <li><a href="../gastos" class="activo">Gastos</a></li>
        <li><a href="../ingresos">Ingresos</a></li>
        <li><a href="../balance">Balance</a></li>
    </ul>
</nav>

<div class="container">

    <div class="page-header">
        <h1>💸 Gestión de Gastos</h1>
        <p>Registra y controla tus gastos personales</p>
    </div>

    <%-- Mensaje de resultado (JSP Expression Language) --%>
    <c:if test="${not empty mensaje}">
        <div class="mensaje ${tipoMensaje}">
            ${mensaje}
        </div>
    </c:if>

    <%-- ===== FORMULARIO HTML - método POST - ===== --%>
    <div class="card">
        <h2>➕ Nuevo Gasto</h2>
        <form action="../gastos" method="post">
            <div class="form-grid">

                <div class="form-group">
                    <label for="descripcion">Descripción</label>
                    <input type="text" id="descripcion" name="descripcion"
                           placeholder="Ej: Mercado semanal" required maxlength="150">
                </div>

                <div class="form-group">
                    <label for="categoria">Categoría</label>
                    <select id="categoria" name="categoria" required>
                        <option value="">-- Seleccione --</option>
                        <option value="Alimentación">🍔 Alimentación</option>
                        <option value="Transporte">🚌 Transporte</option>
                        <option value="Salud">💊 Salud</option>
                        <option value="Educación">📚 Educación</option>
                        <option value="Entretenimiento">🎮 Entretenimiento</option>
                        <option value="Servicios">💡 Servicios</option>
                        <option value="Ropa">👕 Ropa</option>
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

                <div class="form-group">
                    <label for="metodoPago">Método de Pago</label>
                    <select id="metodoPago" name="metodoPago" required>
                        <option value="">-- Seleccione --</option>
                        <option value="Efectivo">💵 Efectivo</option>
                        <option value="Tarjeta débito">💳 Tarjeta débito</option>
                        <option value="Tarjeta crédito">💳 Tarjeta crédito</option>
                        <option value="Transferencia">🏦 Transferencia</option>
                        <option value="Nequi">📱 Nequi</option>
                        <option value="Daviplata">📱 Daviplata</option>
                    </select>
                </div>

            </div>
            <div class="btn-area">
                <button type="submit" class="btn btn-primary">Guardar Gasto</button>
                <button type="reset"  class="btn" style="background:transparent; border:1px solid var(--borde); color:var(--gris); margin-left:.5rem;">Limpiar</button>
            </div>
        </form>
    </div>

    <%-- ===== TABLA DE GASTOS - datos enviados por GET desde el Servlet - ===== --%>
    <div class="card">
        <h2>📋 Historial de Gastos
            <span style="float:right; color:var(--gris); font-size:.85rem; font-weight:400;">
                Total: <strong style="color:var(--rojo);">
                    $<fmt:formatNumber value="${totalGastos}" pattern="#,##0.00"/>
                </strong>
            </span>
        </h2>

        <div class="tabla-container">
            <c:choose>
                <c:when test="${empty listaGastos}">
                    <p style="color:var(--gris); text-align:center; padding:2rem;">
                        No hay gastos registrados aún. ¡Agrega tu primer gasto!
                    </p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Descripción</th>
                                <th>Categoría</th>
                                <th>Monto</th>
                                <th>Fecha</th>
                                <th>Método Pago</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- Iteración con JSTL c:forEach --%>
                            <c:forEach var="gasto" items="${listaGastos}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${gasto.descripcion}</td>
                                    <td>
                                        <%-- Badge de categoría --%>
                                        <span class="badge badge-${fn:toLowerCase(gasto.categoria) == 'alimentación' ? 'alimentacion' :
                                                                     fn:toLowerCase(gasto.categoria) == 'transporte'   ? 'transporte'   :
                                                                     fn:toLowerCase(gasto.categoria) == 'salud'        ? 'salud'        :
                                                                     fn:toLowerCase(gasto.categoria) == 'educación'    ? 'educacion'    : 'otro'}">
                                            ${gasto.categoria}
                                        </span>
                                    </td>
                                    <td style="color:var(--rojo);">
                                        $<fmt:formatNumber value="${gasto.monto}" pattern="#,##0.00"/>
                                    </td>
                                    <td>${gasto.fecha}</td>
                                    <td>${gasto.metodoPago}</td>
                                    <td>
                                        <%-- Eliminar mediante GET con parámetros --%>
                                        <a href="../gastos?accion=eliminar&id=${gasto.id}"
                                           class="btn btn-danger"
                                           onclick="return confirm('¿Eliminar este gasto?')">
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
    // Establece la fecha de hoy por defecto en el formulario
    document.getElementById('fecha').valueAsDate = new Date();
</script>

</body>
</html>
