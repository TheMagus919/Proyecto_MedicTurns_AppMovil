package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Riesgo implements Serializable {
    private int idRiesgo;
    private String nombre;


    public Riesgo() {
    }
    public Riesgo(int idRiesgo, String nombre) {
        this.idRiesgo = idRiesgo;
        this.nombre = nombre;
    }

    public int getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
