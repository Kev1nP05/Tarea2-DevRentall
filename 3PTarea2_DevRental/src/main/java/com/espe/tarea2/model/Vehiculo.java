package com.espe.tarea2.model;

import com.espe.tarea2.excepciones.DatosInvalidosException;


public abstract class Vehiculo {


    private String placa;
    private String marca;
    private String modelo;
    private double precioDia;


    public Vehiculo(String placa, String marca, String modelo, double precioDia) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precioDia = precioDia;
    }

    public Vehiculo() {
        this("", "", "", 0.0);
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser vacía");
        }
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(double precioDia) throws DatosInvalidosException {
        if (precioDia <= 0) {
            throw new DatosInvalidosException("El precio por día debe ser mayor a 0");
        }
        this.precioDia = precioDia;
    }


    public double calcularCostoTotal(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException("Los días deben ser mayores a 0");
        }
        return precioDia * dias;
    }

    @Override
    public String toString() {
        return String.format("Vehículo{placa='%s', marca='%s', modelo='%s', precioDia=%.2f}",
                placa, marca, modelo, precioDia);
    }

    public abstract String getInfoDetallada();
}