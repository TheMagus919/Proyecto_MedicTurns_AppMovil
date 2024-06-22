package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private String clave;
    private String telefono;
    private int idRol;
    private int idEspecialidades;
    private Rol rol;
    private Especialidad especialidad;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String email, String dni, String clave, String telefono, int idRol, int idEspecialidades) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.clave = clave;
        this.telefono = telefono;
        this.idRol = idRol;
        this.idEspecialidades = idEspecialidades;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdEspecialidades() {
        return idEspecialidades;
    }

    public void setIdEspecialidades(int idEspecialidades) {
        this.idEspecialidades = idEspecialidades;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
