package com.example.integrador2.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ReporteTdo implements Serializable {
    private String nombre;
    private int anio;
    private Long inscriptos;
    private Long egresados;

    public ReporteTdo(String nombre, int anio, Long inscriptos, Long egresados) {
        this.nombre = nombre;
        this.anio = anio;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnio() {
        return anio;
    }

    public Long getInscriptos() {
        return inscriptos;
    }

    public Long getEgresados() {
        return egresados;
    }

    @Override
    public String toString() {
        return "ReporteTdo{" +
                "nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                '}';
    }

    public void setGraduados(long graduadosCount) {
        this.egresados=graduadosCount;
    }
}
