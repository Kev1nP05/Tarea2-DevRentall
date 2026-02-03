package com.espe.tarea2.controller;

import com.espe.tarea2.service.VehiculoService;
import com.espe.tarea2.model.Vehiculo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlquilerController implements ActionListener {

    private VehiculoService service;

    public AlquilerController(VehiculoService service) {
        if (service == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        }
        this.service = service;
        System.out.println("Controlador de alquileres inicializado");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println("\nCONTROLADOR ACTIVADO - Procesando solicitud...");

            if (e.getSource() instanceof Vehiculo) {
                Vehiculo vehiculoReal = (Vehiculo) e.getSource();
                procesarVehiculoReal(vehiculoReal);
            } else {

                System.out.println("⚠Modo prueba - No se recibió vehículo del formulario");
            }

        } catch (Exception ex) {
            System.err.println("Error en el controlador: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private void procesarVehiculoReal(Vehiculo vehiculoReal) {
        System.out.println("Procesando vehículo del formulario:");
        System.out.println("   Placa: " + vehiculoReal.getPlaca());
        System.out.println("   Marca: " + vehiculoReal.getMarca());
        System.out.println("   Modelo: " + vehiculoReal.getModelo());
        System.out.println("   Precio/día: $" + vehiculoReal.getPrecioDia());
        System.out.println("   Tipo: " + vehiculoReal.getClass().getSimpleName());

        try {

            service.registrarVehiculo(vehiculoReal);

            System.out.println("Controlador: Vehículo procesado exitosamente");

        } catch (Exception ex) {
            System.err.println("Error al procesar vehículo: " + ex.getMessage());
            throw ex;
        }
    }

    public VehiculoService getService() {
        return service;
    }
}