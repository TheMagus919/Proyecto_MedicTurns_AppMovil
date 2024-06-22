package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Estudio implements Serializable {
    private int idEstudio;
    private String nombre;
    private String descripcion;
    private double precio;
    private String requisitos;
    private int idRiesgo;
    private int idEspecialidades;

    private Riesgo riesgo;
    private Especialidad especialidad;

    public Estudio() {
    }
    public Estudio(int idEstudio, String nombre, String descripcion, double precio, String requisitos, int idRiesgo, int idEspecialidades) {
        this.idEstudio = idEstudio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.requisitos = requisitos;
        this.idRiesgo = idRiesgo;
        this.idEspecialidades = idEspecialidades;
    }

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public int getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public int getIdEspecialidades() {
        return idEspecialidades;
    }

    public void setIdEspecialidades(int idEspecialidades) {
        this.idEspecialidades = idEspecialidades;
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
