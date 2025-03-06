package com.softagus.medicturns.modelo;

import java.io.Serializable;
import java.sql.Time;

public class Horario implements Serializable {
    private int IdHorario;
    private int IdDia;
    private Time HoraInicio;
    private Time HoraFin;
    private int IdUsuario;

    public Horario() {
    }
    public Horario(int idHorario, int idDia, Time horaInicio, Time horaFin, int idUsuario) {
        this.IdHorario = idHorario;
        this.IdDia = idDia;
        this.HoraInicio = horaInicio;
        this.HoraFin = horaFin;
        this.IdUsuario = idUsuario;
    }

    public int getIdHorario() {
        return IdHorario;
    }

    public void setIdHorario(int idHorario) {
        this.IdHorario = idHorario;
    }

    public int getIdDia() {
        return IdDia;
    }

    public void setIdDia(int idDia) {
        this.IdDia = idDia;
    }

    public Time getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.HoraInicio = horaInicio;
    }

    public Time getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(Time horaFin) {
        this.HoraFin = horaFin;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.IdUsuario = idUsuario;
    }
}
