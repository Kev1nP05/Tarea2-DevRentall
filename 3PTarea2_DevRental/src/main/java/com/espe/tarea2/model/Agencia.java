package com.espe.tarea2.model;

import java.util.ArrayList;
import java.util.List;


public class Agencia {
    private String nombre;
    private List<Vehiculo> vehiculos;
    private List<Alquiler> alquileres;

    public Agencia(String nombre) {
        this.nombre = nombre;
        this.vehiculos = new ArrayList<>();
        this.alquileres = new ArrayList<>();
        inicializarVehiculosPredefinidos();
    }

    private void inicializarVehiculosPredefinidos() {
        vehiculos.add(new Coche("ABC123", "Kia", "Rio", 45.00, 4));
        vehiculos.add(new Coche("XYZ789", "Honda", "Civic", 50.00, 2));
        vehiculos.add(new Motocicleta("MOT456", "Yamaha", "MT-07", 30.00, 650));
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public Alquiler registrarAlquiler(Cliente cliente, String placa, int dias) {
        Vehiculo vehiculo = vehiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst()
                .orElse(null);

        if (vehiculo == null) {
            throw new IllegalArgumentException("Vehículo no encontrado con placa: " + placa);
        }

        Alquiler alquiler = new Alquiler(cliente, vehiculo, dias);
        alquileres.add(alquiler);
        return alquiler;
    }

    public List<Vehiculo> getVehiculosDisponibles() {
        return new ArrayList<>(vehiculos);
    }

    public String getNombre() { return nombre; }
    public List<Vehiculo> getVehiculos() { return vehiculos; }
    public List<Alquiler> getAlquileres() { return alquileres; }
}