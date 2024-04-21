package com.example.integrador2.entidades;

import jakarta.persistence.*;
import org.apache.derby.iapi.services.io.LimitInputStream;

import java.io.Serializable;
import java.util.List;

@Entity
public class Estudiante implements Serializable {
    @Id
    private Long dni;
    @Column
    private Long libretaUniversitaria;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String genero;
    @Column int edad;
    @Column
    private String ciudad;
    @OneToMany(mappedBy = "estudiante")
    private List<Carrera_Estudiante> carreras;

    public Estudiante() {
    }

    public Estudiante(Long dni, Long libretaUniversitaria, String nombre, String apellido, String genero,int edad, String ciudad) {
        this.dni = dni;
        this.libretaUniversitaria = libretaUniversitaria;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad=edad;
        this.ciudad = ciudad;
    }

    public Long getDni() {
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
    public int getEdad(){
        return this.edad;
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
    public void setEdad(int e){
        this.edad=e;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
