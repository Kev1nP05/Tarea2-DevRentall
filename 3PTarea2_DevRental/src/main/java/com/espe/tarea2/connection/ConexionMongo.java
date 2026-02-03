package com.espe.tarea2.connection;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;


    private static final String CONNECTION_STRING = "mongodb+srv://KevinParraga:kevin123@cluster0.cao1dxn.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "DevRentalDB2";

    public static MongoDatabase conectar() {
        if (database == null) {
            try {
                System.out.println("Conectando a MongoDB...");
                mongoClient = MongoClients.create(CONNECTION_STRING);
                database = mongoClient.getDatabase(DATABASE_NAME);
                System.out.println("Conectado a MongoDB Atlas");
            } catch (Exception e) {
                System.err.println("Error conexión MongoDB: " + e.getMessage());

                throw new RuntimeException("No se pudo conectar a MongoDB", e);
            }
        }
        return database;
    }

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("🔌 Conexión cerrada");
        }
    }
}