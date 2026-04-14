package com.eoapp.servlet;

import com.eoapp.dao.IngresoDAO;
import com.eoapp.modelo.Ingreso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet para gestión de Ingresos.
 * Maneja GET (listar/eliminar) y POST (registrar nuevo ingreso).
 * EoApp - GA7-220501096-AA2-EV02
 */
@WebServlet("/ingresos")
public class IngresoServlet extends HttpServlet {

    private IngresoDAO ingresoDAO;

    @Override
    public void init() throws ServletException {
        ingresoDAO = new IngresoDAO();
    }

    /**
     * Método GET: Lista todos los ingresos o elimina uno por ID.
     * Parámetro opcional: ?accion=eliminar&id=X
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean eliminado = ingresoDAO.eliminar(id);
            if (eliminado) {
                request.setAttribute("mensaje", "✅ Ingreso eliminado correctamente.");
                request.setAttribute("tipoMensaje", "exito");
            } else {
                request.setAttribute("mensaje", "❌ No se pudo eliminar el ingreso.");
                request.setAttribute("tipoMensaje", "error");
            }
        }

        List<Ingreso> listaIngresos = ingresoDAO.listarTodos();
        double totalIngresos = ingresoDAO.calcularTotal();

        request.setAttribute("listaIngresos", listaIngresos);
        request.setAttribute("totalIngresos", totalIngresos);

        request.getRequestDispatcher("/vistas/ingresos.jsp").forward(request, response);
    }

    /**
     * Método POST: Registra un nuevo ingreso enviado desde el formulario HTML.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String descripcion = request.getParameter("descripcion");
        String fuente      = request.getParameter("fuente");
        double monto       = Double.parseDouble(request.getParameter("monto"));
        LocalDate fecha    = LocalDate.parse(request.getParameter("fecha"));

        Ingreso ingreso = new Ingreso(descripcion, fuente, monto, fecha);
        boolean guardado = ingresoDAO.insertar(ingreso);

        if (guardado) {
            request.setAttribute("mensaje", "✅ Ingreso registrado exitosamente.");
            request.setAttribute("tipoMensaje", "exito");
        } else {
            request.setAttribute("mensaje", "❌ Error al registrar el ingreso.");
            request.setAttribute("tipoMensaje", "error");
        }

        List<Ingreso> listaIngresos = ingresoDAO.listarTodos();
        double totalIngresos = ingresoDAO.calcularTotal();
        request.setAttribute("listaIngresos", listaIngresos);
        request.setAttribute("totalIngresos", totalIngresos);

        request.getRequestDispatcher("/vistas/ingresos.jsp").forward(request, response);
    }
}
