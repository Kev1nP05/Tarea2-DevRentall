package com.espe.tarea2.model;

import com.espe.tarea2.excepciones.DatosInvalidosException;


public class Coche extends Vehiculo {

    private int numPuertas;

    public Coche(String placa, String marca, String modelo, double precioDia, int numPuertas) {
        super(placa, marca, modelo, precioDia);
        setNumPuertas(numPuertas);
    }

    public Coche() {
        super();
        this.numPuertas = 4;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        if (numPuertas < 2 || numPuertas > 5) {
            throw new IllegalArgumentException("El número de puertas debe estar entre 2 y 5");
        }
        this.numPuertas = numPuertas;
    }

    @Override
    public String getInfoDetallada() {
        return String.format("Coche de %d puertas", numPuertas);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", numPuertas=%d", numPuertas);
    }
}