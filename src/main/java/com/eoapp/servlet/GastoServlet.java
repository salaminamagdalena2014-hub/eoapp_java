package com.eoapp.servlet;

import com.eoapp.dao.GastoDAO;
import com.eoapp.modelo.Gasto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet para gestión de Gastos.
 * Maneja GET (listar/eliminar) y POST (registrar nuevo gasto).
 * EoApp - GA7-220501096-AA2-EV02
 */
@WebServlet("/gastos")
public class GastoServlet extends HttpServlet {

    private GastoDAO gastoDAO;

    @Override
    public void init() throws ServletException {
        gastoDAO = new GastoDAO();
    }

    /**
     * Método GET: Lista todos los gastos o elimina uno por ID.
     * Parámetro opcional: ?accion=eliminar&id=X
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            // Eliminar gasto por ID (GET con parámetro)
            int id = Integer.parseInt(request.getParameter("id"));
            boolean eliminado = gastoDAO.eliminar(id);
            if (eliminado) {
                request.setAttribute("mensaje", "✅ Gasto eliminado correctamente.");
                request.setAttribute("tipoMensaje", "exito");
            } else {
                request.setAttribute("mensaje", "❌ No se pudo eliminar el gasto.");
                request.setAttribute("tipoMensaje", "error");
            }
        }

        // Obtener lista actualizada de gastos
        List<Gasto> listaGastos = gastoDAO.listarTodos();
        double totalGastos = gastoDAO.calcularTotal();

        request.setAttribute("listaGastos", listaGastos);
        request.setAttribute("totalGastos", totalGastos);

        // Redirigir a la vista JSP
        request.getRequestDispatcher("/vistas/gastos.jsp").forward(request, response);
    }

    /**
     * Método POST: Registra un nuevo gasto enviado desde el formulario HTML.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Leer parámetros del formulario HTML
        String descripcion = request.getParameter("descripcion");
        String categoria   = request.getParameter("categoria");
        double monto       = Double.parseDouble(request.getParameter("monto"));
        LocalDate fecha    = LocalDate.parse(request.getParameter("fecha"));
        String metodoPago  = request.getParameter("metodoPago");

        // Crear objeto Gasto y guardar
        Gasto gasto = new Gasto(descripcion, categoria, monto, fecha, metodoPago);
        boolean guardado = gastoDAO.insertar(gasto);

        if (guardado) {
            request.setAttribute("mensaje", "✅ Gasto registrado exitosamente.");
            request.setAttribute("tipoMensaje", "exito");
        } else {
            request.setAttribute("mensaje", "❌ Error al registrar el gasto.");
            request.setAttribute("tipoMensaje", "error");
        }

        // Recargar lista actualizada
        List<Gasto> listaGastos = gastoDAO.listarTodos();
        double totalGastos = gastoDAO.calcularTotal();
        request.setAttribute("listaGastos", listaGastos);
        request.setAttribute("totalGastos", totalGastos);

        request.getRequestDispatcher("/vistas/gastos.jsp").forward(request, response);
    }
}
