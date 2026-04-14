package com.eoapp;

import com.eoapp.dao.GastoDAO;
import com.eoapp.modelo.Gasto;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Pruebas unitarias para GastoDAO.
 * EoApp - GA7-220501096-AA2-EV02
 *
 * NOTA: Requiere base de datos activa en localhost.
 * Ejecutar: mvn test
 */
public class GastoDAOTest {

    private GastoDAO gastoDAO;

    @Before
    public void setUp() {
        gastoDAO = new GastoDAO();
    }

    @Test
    public void testInsertarGasto() {
        // Preparar datos de prueba
        Gasto gasto = new Gasto(
            "Test gasto unitario",
            "Otro",
            10000.00,
            LocalDate.now(),
            "Efectivo"
        );
        // Ejecutar inserción
        boolean resultado = gastoDAO.insertar(gasto);
        // Verificar resultado
        assertTrue("El gasto debería insertarse correctamente", resultado);
    }

    @Test
    public void testListarGastos() {
        List<Gasto> lista = gastoDAO.listarTodos();
        // La lista no debe ser nula
        assertNotNull("La lista de gastos no debe ser nula", lista);
    }

    @Test
    public void testCalcularTotal() {
        double total = gastoDAO.calcularTotal();
        // El total debe ser mayor o igual a cero
        assertTrue("El total de gastos debe ser >= 0", total >= 0);
    }

    @Test
    public void testModeloGasto() {
        // Prueba del modelo sin base de datos
        Gasto g = new Gasto();
        g.setId(1);
        g.setDescripcion("Prueba");
        g.setCategoria("Alimentación");
        g.setMonto(50000.00);
        g.setFecha(LocalDate.of(2024, 6, 1));
        g.setMetodoPago("Efectivo");

        assertEquals(1,             g.getId());
        assertEquals("Prueba",      g.getDescripcion());
        assertEquals("Alimentación",g.getCategoria());
        assertEquals(50000.00,      g.getMonto(), 0.01);
        assertEquals("Efectivo",    g.getMetodoPago());
        assertNotNull(g.toString());
    }
}
