package com.eoapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos MySQL.
 * EoApp - GA7-220501096-AA2-EV02
 */
public class ConexionDB {

    private static final String URL      = "jdbc:mysql://localhost:3306/eoapp_db?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER   = "com.mysql.cj.jdbc.Driver";

    /**
     * Obtiene una conexión activa a la base de datos.
     * @return Connection objeto de conexión
     * @throws SQLException si la conexión falla
     */
    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado: " + e.getMessage());
        }
    }

    /**
     * Cierra la conexión de forma segura.
     * @param conexion Connection a cerrar
     */
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando conexión: " + e.getMessage());
            }
        }
    }
}
