package com.espe.tarea2.service;

import com.espe.tarea2.repository.IVehiculoRepository;
import com.espe.tarea2.model.Vehiculo;
import com.espe.tarea2.model.Coche;
import com.espe.tarea2.model.Motocicleta;

public class VehiculoService {

    private IVehiculoRepository repository;

    public VehiculoService(IVehiculoRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repositorio no puede ser nulo");
        }
        this.repository = repository;
        System.out.println("Servicio creado con repositorio inyectado");
    }

    public void registrarVehiculo(Vehiculo v) {
        System.out.println("\n=== SERVICIO PROCESANDO ===");


        validarVehiculo(v);


        System.out.println("Aplicando reglas de negocio...");

        repository.guardar(v);

        System.out.println("Servicio completó el registro");
        System.out.println("============================\n");
    }

    private void validarVehiculo(Vehiculo v) {
        System.out.println("📋 Validando vehículo: " + v.getPlaca());

        if (v.getPlaca() == null || v.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("La placa es requerida");
        }

        if (v.getPrecioDia() <= 0) {
            throw new IllegalArgumentException("Precio debe ser > 0");
        }

        if (v instanceof Coche) {
            Coche coche = (Coche) v;
            if (coche.getNumPuertas() < 2 || coche.getNumPuertas() > 5) {
                throw new IllegalArgumentException("Coche: 2-5 puertas");
            }
        }

        if (v instanceof Motocicleta) {
            Motocicleta moto = (Motocicleta) v;
            if (moto.getCilindrada() < 50 || moto.getCilindrada() > 2000) {
                throw new IllegalArgumentException("Moto: 50-2000cc");
            }
        }

        System.out.println("Validaciones aprobadas");
    }
}