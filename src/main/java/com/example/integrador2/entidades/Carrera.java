package com.example.integrador2.entidades;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera implements Serializable {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrera;
    @Column
    private String nombre;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    private List<Carrera_Estudiante> estudiantes;

    public Carrera() {
    }

    public Carrera(Long id,String nombre, int duracion) {
        this.id_carrera=id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.estudiantes=new ArrayList<>();
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
    public void addEstudianteAlaCarrera(Carrera_Estudiante carEstu){
        this.estudiantes.add(carEstu);
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id_carrera=" + id_carrera +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", estudiantes=" + estudiantes +
                '}';
    }
}
