package com.eoapp.servlet;

import com.eoapp.dao.GastoDAO;
import com.eoapp.dao.IngresoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet para mostrar el balance general (ingresos - gastos).
 * Solo usa GET para consultar y mostrar el resumen financiero.
 * EoApp - GA7-220501096-AA2-EV02
 */
@WebServlet("/balance")
public class BalanceServlet extends HttpServlet {

    private GastoDAO gastoDAO;
    private IngresoDAO ingresoDAO;

    @Override
    public void init() throws ServletException {
        gastoDAO   = new GastoDAO();
        ingresoDAO = new IngresoDAO();
    }

    /**
     * Método GET: Calcula y muestra el balance financiero personal.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double totalIngresos = ingresoDAO.calcularTotal();
        double totalGastos   = gastoDAO.calcularTotal();
        double balance       = totalIngresos - totalGastos;
        String estadoBalance = balance >= 0 ? "positivo" : "negativo";

        request.setAttribute("totalIngresos", totalIngresos);
        request.setAttribute("totalGastos",   totalGastos);
        request.setAttribute("balance",        balance);
        request.setAttribute("estadoBalance",  estadoBalance);

        request.getRequestDispatcher("/vistas/balance.jsp").forward(request, response);
    }
}
