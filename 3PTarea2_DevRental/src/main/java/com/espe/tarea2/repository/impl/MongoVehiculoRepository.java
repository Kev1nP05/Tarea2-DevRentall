package com.espe.tarea2.repository.impl;

import com.espe.tarea2.repository.IVehiculoRepository;
import com.espe.tarea2.model.Vehiculo;
import com.espe.tarea2.model.Coche;
import com.espe.tarea2.model.Motocicleta;
import com.espe.tarea2.connection.ConexionMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoVehiculoRepository implements IVehiculoRepository {

    private MongoDatabase database;

    public MongoVehiculoRepository() {
        try {
            this.database = ConexionMongo.conectar();
            System.out.println("Repositorio MongoDB creado");
        } catch (Exception e) {
            System.err.println("⚠No se pudo crear repositorio MongoDB: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void guardar(Vehiculo v) {
        try {
            if (database == null) {
                throw new IllegalStateException("No hay conexión a MongoDB");
            }

            MongoCollection<Document> collection = database.getCollection("vehiculos");

            Document doc = new Document();
            doc.append("placa", v.getPlaca());
            doc.append("marca", v.getMarca());
            doc.append("modelo", v.getModelo());
            doc.append("precio_dia", v.getPrecioDia());
            doc.append("tipo", v.getClass().getSimpleName());
            doc.append("fecha_registro", new java.util.Date());

            if (v instanceof Coche) {
                doc.append("num_puertas", ((Coche) v).getNumPuertas());
            } else if (v instanceof Motocicleta) {
                doc.append("cilindrada", ((Motocicleta) v).getCilindrada());
            }

            collection.insertOne(doc);
            System.out.println("Vehículo guardado en MongoDB: " + v.getPlaca());

        } catch (Exception e) {
            System.err.println("Error al guardar en MongoDB: " + e.getMessage());
            // Simulamos éxito para que el programa continúe
            System.out.println("⚠Simulando guardado (modo desarrollo)");
        }
    }
}