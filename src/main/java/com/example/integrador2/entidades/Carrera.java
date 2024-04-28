package com.example.integrador2.entidades;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de persistir en la base de datos a la entidad Carrera
 * y sus atributos y metodos relacionados
 * @version 25/4/2024
 */
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

    /**
     * Constructor vacio, permite crear un objeto Carrera sin valor en sus atributos
     */
    public Carrera() {
    }

    /**
     * Constructor que recibe sus parametros y crean al objeto
     * @param id de tipo Long , es el identificador de la carrera
     * @param nombre de tipo String, es el nombre de la carrera
     * @param duracion de tipo int, es la duracion de la carrera
     */
    public Carrera(Long id,String nombre, int duracion) {
        this.id_carrera=id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.estudiantes=new ArrayList<>();
    }

    /**
     * Metodo que retorna el identificador de la Carrera
     * @return devuelve un Long del id carrera
     */
    public Long getId_carrera() {
        return id_carrera;
    }

    /**
     * Metodo que retorna el nombre de la carrera
     * @return devuelve un String de la carrera
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que devuelve la duracion de la carrera
     * @return retorna un int que devuelve la duracion de la carrera
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Metodo encargado de agregar un estudiante a la carrera
     * @param carEstu es un Carrera_Estudiante que guarda la relacion de la carrera
     *                con el estudiante
     */
    public void addEstudianteAlaCarrera(Carrera_Estudiante carEstu){
        this.estudiantes.add(carEstu);
    }

    /**
     * Metodo para setear el nombre de la carrera
     * @param nombre es el nuevo nombre que pisara al antiguo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para setear la duracion de la carrera
     * @param duracion nuevo valor que pisara al antiguo
     */
    public void setDuracion(int duracion) {
        if(duracion>0){
            this.duracion = duracion;
        }
    }

    /**
     * Metodo que imprime al objeto y sus atributos
     * @return retorna un String con todos los datos de la Carrera
     */
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
