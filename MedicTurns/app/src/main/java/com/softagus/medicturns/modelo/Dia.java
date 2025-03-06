package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Dia implements Serializable {

    private int IdDia;
    private String Nombre;


    public Dia() {
    }
    public Dia(int idDia, String nombre) {
        this.IdDia = idDia;
        this.Nombre = nombre;
    }

    public int getIdDia() {
        return IdDia;
    }

    public void setIdDia(int idDia) {
        this.IdDia = idDia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
}
