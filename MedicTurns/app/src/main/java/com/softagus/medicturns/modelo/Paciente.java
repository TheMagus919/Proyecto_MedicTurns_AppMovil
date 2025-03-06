package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Paciente implements Serializable {
    private int IdPaciente;
    private String Nombre;
    private String Apellido;
    private String Dni;
    private String Cuil;
    private String Email;
    private String Telefono;
    private String ObraSocial;
    private String Direccion;
    private String GrupoSanguineo;
    private String Alergias;
    private int IdRiesgo;
    private Riesgo riesgo;

    public Paciente() {
    }

    public Paciente(int idPaciente, String nombre, String apellido, String dni, String cuil, String email, String telefono, String obraSocial, String direccion, String grupoSanguineo, String alergias, int idRiesgo) {
        this.IdPaciente = idPaciente;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Dni = dni;
        this.Cuil = cuil;
        this.Email = email;
        this.Telefono = telefono;
        this.ObraSocial = obraSocial;
        this.Direccion = direccion;
        this.GrupoSanguineo = grupoSanguineo;
        this.Alergias = alergias;
        this.IdRiesgo = idRiesgo;
    }

    public Paciente(int idPaciente, String nombre, String apellido, String dni, String cuil, String email, String telefono, String obraSocial, String direccion, String grupoSanguineo, String alergias, int idRiesgo, Riesgo riesgo) {
        this.IdPaciente = idPaciente;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Dni = dni;
        this.Cuil = cuil;
        this.Email = email;
        this.Telefono = telefono;
        this.ObraSocial = obraSocial;
        this.Direccion = direccion;
        this.GrupoSanguineo = grupoSanguineo;
        this.Alergias = alergias;
        this.IdRiesgo = idRiesgo;
        this.riesgo = riesgo;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.IdPaciente = idPaciente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        this.Dni = dni;
    }

    public String getCuil() {
        return Cuil;
    }

    public void setCuil(String cuil) {
        this.Cuil = cuil;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String getObraSocial() {
        return ObraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.ObraSocial = obraSocial;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public String getGrupoSanguineo() {
        return GrupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.GrupoSanguineo = grupoSanguineo;
    }

    public String getAlergias() {
        return Alergias;
    }

    public void setAlergias(String alergias) {
        this.Alergias = alergias;
    }

    public int getIdRiesgo() {
        return IdRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.IdRiesgo = idRiesgo;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }
}
