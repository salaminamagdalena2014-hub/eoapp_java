package com.eoapp.dao;

import com.eoapp.modelo.Ingreso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para operaciones CRUD de Ingreso.
 * EoApp - GA7-220501096-AA2-EV02
 */
public class IngresoDAO {

    /**
     * Inserta un nuevo ingreso en la base de datos.
     * @param ingreso objeto Ingreso a insertar
     * @return true si se insertó correctamente
     */
    public boolean insertar(Ingreso ingreso) {
        String sql = "INSERT INTO ingresos (descripcion, fuente, monto, fecha) VALUES (?, ?, ?, ?)";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ingreso.getDescripcion());
            ps.setString(2, ingreso.getFuente());
            ps.setDouble(3, ingreso.getMonto());
            ps.setDate(4, Date.valueOf(ingreso.getFecha()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insertando ingreso: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(con);
        }
    }

    /**
     * Lista todos los ingresos registrados.
     * @return List<Ingreso> lista de ingresos
     */
    public List<Ingreso> listarTodos() {
        List<Ingreso> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingresos ORDER BY fecha DESC";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Ingreso i = new Ingreso();
                i.setId(rs.getInt("id"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setFuente(rs.getString("fuente"));
                i.setMonto(rs.getDouble("monto"));
                i.setFecha(rs.getDate("fecha").toLocalDate());
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error listando ingresos: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(con);
        }
        return lista;
    }

    /**
     * Elimina un ingreso por su ID.
     * @param id identificador del ingreso
     * @return true si se eliminó correctamente
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM ingresos WHERE id = ?";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminando ingreso: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(con);
        }
    }

    /**
     * Calcula la suma total de todos los ingresos.
     * @return double total de ingresos
     */
    public double calcularTotal() {
        String sql = "SELECT COALESCE(SUM(monto), 0) AS total FROM ingresos";
        Connection con = null;
        try {
            con = ConexionDB.obtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) return rs.getDouble("total");
        } catch (SQLException e) {
            System.err.println("Error calculando total ingresos: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(con);
        }
        return 0;
    }
}
