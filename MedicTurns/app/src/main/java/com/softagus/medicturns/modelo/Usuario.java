package com.softagus.medicturns.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int IdUsuario;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Dni;
    private String Clave;
    private String Telefono;
    private int IdRol;
    private int IdEspecialidades;
    private Rol rol;
    private Especialidad especialidad;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String email, String dni, String clave, String telefono, int idRol, int idEspecialidades) {
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Email = email;
        this.Dni = dni;
        this.Clave = clave;
        this.Telefono = telefono;
        this.IdRol = idRol;
        this.IdEspecialidades = idEspecialidades;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.IdUsuario = idUsuario;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        this.Dni = dni;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        this.Clave = clave;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        this.IdRol = idRol;
    }

    public int getIdEspecialidades() {
        return IdEspecialidades;
    }

    public void setIdEspecialidades(int idEspecialidades) {
        this.IdEspecialidades = idEspecialidades;
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
