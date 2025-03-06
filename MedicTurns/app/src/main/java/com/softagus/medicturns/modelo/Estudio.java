package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Estudio implements Serializable {
    private int IdEstudio;
    private String Nombre;
    private String Descripcion;
    private double Precio;
    private String Requisitos;
    private int IdRiesgo;
    private int IdEspecialidades;

    private Riesgo riesgo;
    private Especialidad especialidad;

    public Estudio() {
    }
    public Estudio(int idEstudio, String nombre, String descripcion, double precio, String requisitos, int idRiesgo, int idEspecialidades) {
        this.IdEstudio = idEstudio;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Precio = precio;
        this.Requisitos = requisitos;
        this.IdRiesgo = idRiesgo;
        this.IdEspecialidades = idEspecialidades;
    }

    public int getIdEstudio() {
        return IdEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.IdEstudio = idEstudio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        this.Precio = precio;
    }

    public String getRequisitos() {
        return Requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.Requisitos = requisitos;
    }

    public int getIdRiesgo() {
        return IdRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.IdRiesgo = idRiesgo;
    }

    public int getIdEspecialidades() {
        return IdEspecialidades;
    }

    public void setIdEspecialidades(int idEspecialidades) {
        this.IdEspecialidades = idEspecialidades;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
