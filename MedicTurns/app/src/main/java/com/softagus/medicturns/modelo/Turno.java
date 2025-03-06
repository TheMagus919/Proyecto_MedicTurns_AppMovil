package com.softagus.medicturns.modelo;

import java.io.Serializable;
import java.util.Date;

public class Turno implements Serializable {

    private int IdTurno;
    private int IdUsuario;
    private int IdPaciente;
    private int IdEstudio;
    private String FechaTurno;
    private String FechaFin;
    private boolean Asistio;
    private String observaciones;
    private Estudio estudio;
    private Paciente paciente;
    private Usuario usuario;

    public Turno() {
    }

    public Turno(int idTurno, int idUsuario, int idPaciente, int idEstudio, String fechaTurno, String fechaFin, boolean asistio, String observaciones) {
        this.IdTurno = idTurno;
        this.IdUsuario = idUsuario;
        this.IdPaciente = idPaciente;
        this.IdEstudio = idEstudio;
        this.FechaTurno = fechaTurno;
        this.FechaFin = fechaFin;
        this.Asistio = asistio;
        this.observaciones = observaciones;
    }

    public Turno(int idTurno, int idUsuario, int idPaciente, int idEstudio, String fechaTurno, String fechaFin, boolean asistio, String observaciones, Estudio estudio, Paciente paciente, Usuario usuario) {
        this.IdTurno = idTurno;
        this.IdUsuario = idUsuario;
        this.IdPaciente = idPaciente;
        this.IdEstudio = idEstudio;
        this.FechaTurno = fechaTurno;
        this.FechaFin = fechaFin;
        this.Asistio = asistio;
        this.observaciones = observaciones;
        this.estudio = estudio;
        this.paciente = paciente;
        this.usuario = usuario;
    }

    public int getIdTurno() {
        return IdTurno;
    }

    public void setIdTurno(int idTurno) {
        this.IdTurno = idTurno;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.IdPaciente = idPaciente;
    }

    public int getIdEstudio() {
        return IdEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.IdEstudio = idEstudio;
    }

    public String getFechaTurno() {
        return FechaTurno;
    }

    public void setFechaTurno(String fechaTurno) {
        this.FechaTurno = fechaTurno;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.FechaFin = fechaFin;
    }

    public boolean isAsistio() {
        return Asistio;
    }

    public void setAsistio(boolean asistio) {
        this.Asistio = asistio;
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
