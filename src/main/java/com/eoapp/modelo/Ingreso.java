package com.eoapp.modelo;

import java.time.LocalDate;

/**
 * Clase modelo que representa un Ingreso personal.
 * EoApp - GA7-220501096-AA2-EV02
 */
public class Ingreso {

    private int id;
    private String descripcion;
    private String fuente;
    private double monto;
    private LocalDate fecha;

    public Ingreso() {}

    public Ingreso(int id, String descripcion, String fuente,
                   double monto, LocalDate fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fuente = fuente;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Ingreso(String descripcion, String fuente,
                   double monto, LocalDate fecha) {
        this.descripcion = descripcion;
        this.fuente = fuente;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFuente() { return fuente; }
    public void setFuente(String fuente) { this.fuente = fuente; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return "Ingreso{id=" + id + ", descripcion='" + descripcion +
               "', fuente='" + fuente + "', monto=" + monto +
               ", fecha=" + fecha + "}";
    }
}
