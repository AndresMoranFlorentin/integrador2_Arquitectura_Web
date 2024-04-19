package com.example.integrador2.entidades;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_carrera;
    @Column
    private String nombre;
    @Column
    private int duracion;
    @ManyToMany
    private List<Estudiante> estudiantes;

    public Carrera() {
    }

    public Carrera(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
