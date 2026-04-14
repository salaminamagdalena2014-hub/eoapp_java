package com.eoapp.dao;

import com.eoapp.modelo.Gasto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para operaciones CRUD de Gasto.
 * EoApp - GA7-220501096-AA2-EV02
 */
public class GastoDAO {

    /**
     * Inserta un nuevo gasto en la base de datos.
     * @param gasto objeto Gasto a insertar
     * @return true si se insertó correctamente
     */
    public boolean insertar(Gasto gasto) {
        String sql = "INSERT INTO gastos (descripcion, categoria, monto, fecha, metodo_pago) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, gasto.getDescripcion());
            ps.setString(2, gasto.getCategoria());
            ps.setDouble(3, gasto.getMonto());
            ps.setDate(4, Date.valueOf(gasto.getFecha()));
            ps.setString(5, gasto.getMetodoPago());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insertando gasto: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(con);
        }
    }

    /**
     * Lista todos los gastos registrados.
     * @return List<Gasto> lista de gastos
     */
    public List<Gasto> listarTodos() {
        List<Gasto> lista = new ArrayList<>();
        String sql = "SELECT * FROM gastos ORDER BY fecha DESC";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Gasto g = new Gasto();
                g.setId(rs.getInt("id"));
                g.setDescripcion(rs.getString("descripcion"));
                g.setCategoria(rs.getString("categoria"));
                g.setMonto(rs.getDouble("monto"));
                g.setFecha(rs.getDate("fecha").toLocalDate());
                g.setMetodoPago(rs.getString("metodo_pago"));
                lista.add(g);
            }
        } catch (SQLException e) {
            System.err.println("Error listando gastos: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(con);
        }
        return lista;
    }

    /**
     * Busca un gasto por su ID.
     * @param id identificador del gasto
     * @return Gasto encontrado o null
     */
    public Gasto buscarPorId(int id) {
        String sql = "SELECT * FROM gastos WHERE id = ?";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Gasto g = new Gasto();
                g.setId(rs.getInt("id"));
                g.setDescripcion(rs.getString("descripcion"));
                g.setCategoria(rs.getString("categoria"));
                g.setMonto(rs.getDouble("monto"));
                g.setFecha(rs.getDate("fecha").toLocalDate());
                g.setMetodoPago(rs.getString("metodo_pago"));
                return g;
            }
        } catch (SQLException e) {
            System.err.println("Error buscando gasto: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(con);
        }
        return null;
    }

    /**
     * Elimina un gasto por su ID.
     * @param id identificador del gasto
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM gastos WHERE id = ?";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminando gasto: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(con);
        }
    }

    /**
     * Calcula la suma total de todos los gastos.
     * @return double total de gastos
     */
    public double calcularTotal() {
        String sql = "SELECT COALESCE(SUM(monto), 0) AS total FROM gastos";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) return rs.getDouble("total");
        } catch (SQLException e) {
            System.err.println("Error calculando total gastos: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(con);
        }
        return 0;
    }
}
