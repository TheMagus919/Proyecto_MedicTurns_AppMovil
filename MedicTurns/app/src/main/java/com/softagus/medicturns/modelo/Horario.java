package com.softagus.medicturns.modelo;

import java.io.Serializable;
import java.sql.Time;

public class Horario implements Serializable {
    private int idHorario;
    private int idDia;
    private Time horaInicio;
    private Time horaFin;
    private int idUsuario;

    public Horario() {
    }
    public Horario(int idHorario, int idDia, Time horaInicio, Time horaFin, int idUsuario) {
        this.idHorario = idHorario;
        this.idDia = idDia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idUsuario = idUsuario;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
