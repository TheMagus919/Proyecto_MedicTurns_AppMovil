package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Paciente implements Serializable {
    private int idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private String cuil;
    private String email;
    private String telefono;
    private String obraSocial;
    private String direccion;
    private String grupoSanguineo;
    private String alergias;
    private int idRiesgo;
    private Riesgo riesgo;

    public Paciente() {
    }

    public Paciente(int idPaciente, String nombre, String apellido, String dni, String cuil, String email, String telefono, String obraSocial, String direccion, String grupoSanguineo, String alergias, int idRiesgo) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuil = cuil;
        this.email = email;
        this.telefono = telefono;
        this.obraSocial = obraSocial;
        this.direccion = direccion;
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = alergias;
        this.idRiesgo = idRiesgo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public int getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }
}
