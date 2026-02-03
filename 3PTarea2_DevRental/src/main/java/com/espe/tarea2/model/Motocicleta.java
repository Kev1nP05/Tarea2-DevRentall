package com.espe.tarea2.model;


public class Motocicleta extends Vehiculo {

    private int cilindrada;

    public Motocicleta(String placa, String marca, String modelo, double precioDia, int cilindrada) {
        super(placa, marca, modelo, precioDia);
        setCilindrada(cilindrada);
    }

    public Motocicleta() {
        super();
        this.cilindrada = 125;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        if (cilindrada < 50 || cilindrada > 2000) {
            throw new IllegalArgumentException("La cilindrada debe estar entre 50cc y 2000cc");
        }
        this.cilindrada = cilindrada;
    }

    @Override
    public String getInfoDetallada() {
        return String.format("Motocicleta de %d cc", cilindrada);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", cilindrada=%dcc", cilindrada);
    }
}