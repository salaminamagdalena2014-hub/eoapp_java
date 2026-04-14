package com.eoapp.modelo;

import java.time.LocalDate;

/**
 * Clase modelo que representa un Gasto personal.
 * EoApp - GA7-220501096-AA2-EV02
 */
public class Gasto {

    private int id;
    private String descripcion;
    private String categoria;
    private double monto;
    private LocalDate fecha;
    private String metodoPago;

    public Gasto() {}

    public Gasto(int id, String descripcion, String categoria,
                 double monto, LocalDate fecha, String metodoPago) {
        this.id = id;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
    }

    public Gasto(String descripcion, String categoria,
                 double monto, LocalDate fecha, String metodoPago) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    @Override
    public String toString() {
        return "Gasto{id=" + id + ", descripcion='" + descripcion +
               "', categoria='" + categoria + "', monto=" + monto +
               ", fecha=" + fecha + "}";
    }
}
