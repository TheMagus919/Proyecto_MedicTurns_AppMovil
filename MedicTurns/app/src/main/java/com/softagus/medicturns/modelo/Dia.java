package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Dia implements Serializable {

    private int idDia;
    private String nombre;


    public Dia() {
    }
    public Dia(int idDia, String nombre) {
        this.idDia = idDia;
        this.nombre = nombre;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
