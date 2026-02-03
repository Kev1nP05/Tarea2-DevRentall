package com.espe.tarea2.model;


import java.util.Date;
import java.text.SimpleDateFormat;


public class Alquiler {
    private String id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Date fechaInicio;
    private int dias;
    private double costoTotal;

    public Alquiler(Cliente cliente, Vehiculo vehiculo, int dias) {
        this.id = "ALQ" + System.currentTimeMillis();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = new Date();
        this.dias = dias;
        this.costoTotal = vehiculo.calcularCostoTotal(dias);
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public Date getFechaInicio() { return fechaInicio; }
    public int getDias() { return dias; }
    public double getCostoTotal() { return costoTotal; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return String.format(
                "Alquiler ID: %s\nCliente: %s\nVehículo: %s\n" +
                        "Fecha: %s\nDías: %d\nCosto Total: $%.2f",
                id, cliente.getNombre(), vehiculo.toString(),
                sdf.format(fechaInicio), dias, costoTotal
        );
    }
}