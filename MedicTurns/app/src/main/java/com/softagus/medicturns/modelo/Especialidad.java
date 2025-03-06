package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Especialidad implements Serializable {
    private int IdEspecialidades;
    private String Nombre;

    public Especialidad() {
    }
    public Especialidad(int idEspecialidades, String nombre) {
        this.IdEspecialidades = idEspecialidades;
        this.Nombre = nombre;
    }

    public int getIdEspecialidades() {
        return IdEspecialidades;
    }

    public void setIdEspecialidades(int idEspecialidades) {
        this.IdEspecialidades = idEspecialidades;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
}
