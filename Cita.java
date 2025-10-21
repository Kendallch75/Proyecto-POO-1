package com.mycompany.proyectopoo1;


import java.io.*;
import java.util.*;


public class Cita {
    public Cita() {
    }
    private Paciente paciente;
    private Personal profesional;
    private String estado;
    private Consultorio consultorio;
    private String fecha;
    private String Hora;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Personal getProfesional() {
        return profesional;
    }

    public void setProfesional(Personal profesional) {
        this.profesional = profesional;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }
    
    
}
