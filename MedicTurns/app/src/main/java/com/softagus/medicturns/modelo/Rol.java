package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Rol implements Serializable {
    private int IdRol;
    private String Nombre;

    public Rol() {
    }
    public Rol(int idRol, String nombre) {
        this.IdRol = idRol;
        this.Nombre = nombre;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        this.IdRol = idRol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
}
