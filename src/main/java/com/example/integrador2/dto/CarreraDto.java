package com.example.integrador2.dto;

import jakarta.persistence.Column;

public class CarreraDto {
    private Long id_carrera;
    private String nombre;
    private int duracion;
    private int cant_inscriptos;

    public CarreraDto(Long id_carrera, String nombre, int duracion, int cant_inscriptos) {
        this.id_carrera = id_carrera;
        this.nombre = nombre;
        this.duracion = duracion;
        this.cant_inscriptos = cant_inscriptos;
    }

    public Long getId_carrera() {
        return id_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getCant_inscriptos() {
        return cant_inscriptos;
    }

    @Override
    public String toString() {
        return "CarreraDto{" +
                "id_carrera=" + id_carrera +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", cant_inscriptos=" + cant_inscriptos +
                '}';
    }
}
