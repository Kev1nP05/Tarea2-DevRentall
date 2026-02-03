package com.espe.tarea2.model;



public class Cliente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;

    public Cliente(String cedula, String nombre, String telefono, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Cliente{cedula='%s', nombre='%s', telefono='%s', email='%s'}",
                cedula, nombre, telefono, email);
    }
}