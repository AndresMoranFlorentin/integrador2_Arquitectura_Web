package com.example.integrador2.entidades;

import jakarta.persistence.*;
import org.apache.derby.iapi.services.io.LimitInputStream;

import java.util.List;

@Entity
public class Estudiante {
    @Id
    private int dni;
    @Column
    private Long libretaUniversitaria;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String genero;
    @Column
    private String ciudad;
    @ManyToMany
    private List<Carrera> carreras;

    public Estudiante() {
    }

    public Estudiante(int dni, Long libretaUniversitaria, String nombre, String apellido, String genero, String ciudad) {
        this.dni = dni;
        this.libretaUniversitaria = libretaUniversitaria;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.ciudad = ciudad;
    }

    public int getDni() {
        return dni;
    }

    public Long getLibretaUniversitaria() {
        return libretaUniversitaria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
