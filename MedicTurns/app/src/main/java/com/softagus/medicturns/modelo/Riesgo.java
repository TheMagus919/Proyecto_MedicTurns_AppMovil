package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Riesgo implements Serializable {
    private int IdRiesgo;
    private String Nombre;


    public Riesgo() {
    }
    public Riesgo(int idRiesgo, String nombre) {
        this.IdRiesgo = idRiesgo;
        this.Nombre = nombre;
    }

    public int getIdRiesgo() {
        return IdRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.IdRiesgo = idRiesgo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
}
