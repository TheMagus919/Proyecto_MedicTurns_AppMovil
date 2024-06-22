package com.softagus.medicturns.modelo;

import java.io.Serializable;
import java.util.Date;

public class Turno implements Serializable {

    private int idTurno;
    private int idUsuario;
    private int idPaciente;
    private int idEstudio;
    private Date fechaTurno;
    private Date fechaFin;
    private boolean asistio;
    private String observaciones;
    private Estudio estudio;
    private Paciente paciente;
    private Usuario usuario;

    public Turno() {
    }

    public Turno(int idTurno, int idUsuario, int idPaciente, int idEstudio, Date fechaTurno, Date fechaFin, boolean asistio, String observaciones) {
        this.idTurno = idTurno;
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
        this.idEstudio = idEstudio;
        this.fechaTurno = fechaTurno;
        this.fechaFin = fechaFin;
        this.asistio = asistio;
        this.observaciones = observaciones;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public Date getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(Date fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
