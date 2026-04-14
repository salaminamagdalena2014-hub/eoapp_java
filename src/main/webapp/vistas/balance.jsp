<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EoApp - Balance</title>
    <link rel="stylesheet" href="../css/estilos.css">
</head>
<body>

<nav class="navbar">
    <a href="../index.jsp" class="logo">Eo<span>App</span></a>
    <ul class="nav-links">
        <li><a href="../index.jsp">Inicio</a></li>
        <li><a href="../gastos">Gastos</a></li>
        <li><a href="../ingresos">Ingresos</a></li>
        <li><a href="../balance" class="activo">Balance</a></li>
    </ul>
</nav>

<div class="container">

    <div class="page-header">
        <h1>📊 Balance General</h1>
        <p>Resumen de tu situación financiera personal</p>
    </div>

    <%-- ===== Resumen de totales usando EL (Expression Language de JSP) ===== --%>
    <div class="resumen-grid">

        <div class="resumen-card verde">
            <div class="etiqueta">💰 Total Ingresos</div>
            <div class="valor">
                $<fmt:formatNumber value="${totalIngresos}" pattern="#,##0.00"/>
            </div>
            <div class="etiqueta">Dinero recibido</div>
        </div>

        <div class="resumen-card rojo">
            <div class="etiqueta">💸 Total Gastos</div>
            <div class="valor">
                $<fmt:formatNumber value="${totalGastos}" pattern="#,##0.00"/>
            </div>
            <div class="etiqueta">Dinero gastado</div>
        </div>

        <div class="resumen-card azul">
            <div class="etiqueta">📊 Balance</div>
            <div class="valor ${estadoBalance == 'positivo' ? 'balance-positivo' : 'balance-negativo'}">
                $<fmt:formatNumber value="${balance}" pattern="#,##0.00"/>
            </div>
            <div class="etiqueta">
                <c:choose>
                    <c:when test="${estadoBalance == 'positivo'}">✅ Estás en positivo</c:when>
                    <c:otherwise>⚠️ Gastas más de lo que ganas</c:otherwise>
                </c:choose>
            </div>
        </div>

    </div>

    <%-- ===== Indicador visual de ahorro ===== --%>
    <div class="card">
        <h2>📈 Indicador de Ahorro</h2>
        <c:if test="${totalIngresos > 0}">
            <%-- Calcular porcentaje de gasto con JSP scriptlet --%>
            <%
                double ingresos = (Double) request.getAttribute("totalIngresos");
                double gastos   = (Double) request.getAttribute("totalGastos");
                double porcentajeGasto = (ingresos > 0) ? Math.min((gastos / ingresos) * 100, 100) : 0;
                double porcentajeAhorro = Math.max(100 - porcentajeGasto, 0);
                request.setAttribute("porcentajeGasto", porcentajeGasto);
                request.setAttribute("porcentajeAhorro", porcentajeAhorro);
            %>
            <p style="margin-bottom:.5rem; color:var(--gris);">
                Estás usando el <strong style="color:var(--rojo);">
                    <fmt:formatNumber value="${porcentajeGasto}" maxFractionDigits="1"/>%
                </strong> de tus ingresos en gastos.
                Tasa de ahorro: <strong style="color:var(--verde);">
                    <fmt:formatNumber value="${porcentajeAhorro}" maxFractionDigits="1"/>%
                </strong>
            </p>
            <div class="barra-progreso">
                <div class="barra-fill" style="width: ${porcentajeAhorro}%;"></div>
            </div>
        </c:if>
        <c:if test="${totalIngresos == 0}">
            <p style="color:var(--gris);">Registra ingresos para ver el indicador de ahorro.</p>
        </c:if>
    </div>

    <%-- ===== Recomendación condicional con JSTL ===== --%>
    <div class="card">
        <h2>💡 Recomendación</h2>
        <c:choose>
            <c:when test="${balance > 0 and balance >= totalIngresos * 0.2}">
                <p style="color:var(--verde);">
                    🎉 Excelente gestión financiera. Estás ahorrando más del 20% de tus ingresos.
                    Considera invertir el excedente.
                </p>
            </c:when>
            <c:when test="${balance > 0}">
                <p style="color:var(--azul);">
                    👍 Vas bien. Tienes un balance positivo aunque modesto.
                    Intenta reducir gastos en categorías no esenciales.
                </p>
            </c:when>
            <c:when test="${balance == 0}">
                <p style="color:var(--gris);">
                    ⚖️ Tu balance es cero. No estás ahorrando nada.
                    Revisa tus gastos para generar un margen de ahorro.
                </p>
            </c:when>
            <c:otherwise>
                <p style="color:var(--rojo);">
                    ⚠️ Tus gastos superan tus ingresos. Revisa urgente tus gastos
                    y busca formas de aumentar tus ingresos o reducir gastos.
                </p>
            </c:otherwise>
        </c:choose>
    </div>

    <div style="text-align:center; margin-top:1rem;">
        <a href="../gastos"  class="btn btn-primary" style="margin:.3rem;">➕ Agregar Gasto</a>
        <a href="../ingresos" class="btn btn-primary" style="background:#3498db; margin:.3rem;">💵 Agregar Ingreso</a>
    </div>

</div>

<footer>
    EoApp &copy; 2024 &mdash; GA7-220501096-AA2-EV02 | SENA ADSO
</footer>

</body>
</html>
