package com.example.integrador2.dto;

import jakarta.persistence.Column;

/**
 * Esta es una clase que sirve como vista de la entidad Carrera para evitar exponer
 * a la entidad Carrera y sus datos con una nueva columna de los inscriptos
 * de las Carreras y solo tiene metodos get que muestran datos
 * @version 25/4/2024 A.0
 */
public class CarreraDto {
    private Long id_carrera;
    private String nombre;
    private int duracion;
    private int cant_inscriptos;

    /**
     *
     * @param id_carrera es el identificador de la carrera
     * @param nombre es el nombre que tendra la carrera
     * @param duracion es el número de años que tendra la carrera
     * @param cant_inscriptos es la cantidad de inscriptos que tiene la carrera
     */
    public CarreraDto(Long id_carrera, String nombre, int duracion, int cant_inscriptos) {
        this.id_carrera = id_carrera;
        this.nombre = nombre;
        this.duracion = duracion;
        this.cant_inscriptos = cant_inscriptos;
    }

    /**
     * Metodo que devuelve él, id de la carrera
     * @return retorna el identificador de la carrera
     */
    public Long getId_carrera() {
        return id_carrera;
    }

    /**
     * Metodo que retorna el nombre de la carrera
     * @return retorna el nombre de la carrera
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que devuelve la duracion de la carrera
     * @return devuelve la duracion de la carrera
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Metodo que retorna la cantidad de inscriptos que tiene la carrera
     * @return retorna la cantidad de inscriptos
     */
    public int getCant_inscriptos() {
        return cant_inscriptos;
    }

    /**
     * Metodo que imprime los datos de la carrera
     * @return retorna un string que muestra todos los datos del objeto CarreraDto
     */
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
