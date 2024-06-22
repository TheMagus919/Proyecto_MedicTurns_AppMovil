package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Especialidad implements Serializable {
    private int idEspecialidades;
    private String nombre;

    public Especialidad() {
    }
    public Especialidad(int idEspecialidades, String nombre) {
        this.idEspecialidades = idEspecialidades;
        this.nombre = nombre;
    }

    public int getIdEspecialidades() {
        return idEspecialidades;
    }

    public void setIdEspecialidades(int idEspecialidades) {
        this.idEspecialidades = idEspecialidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
