package com.example.integrador2.entidades;

import jakarta.persistence.*;
import org.apache.derby.iapi.services.io.LimitInputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de persistir en la base de datos a la entidad Estudiante
 * que muestra sus atributos y metodos relacionados para setear los datos
 * o pedirlos
 * @version 25/4/2024
 */
@Entity
public class Estudiante implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * Constructor que recibe todos los datos de un estudiante
     * @param dni el dni del estudiante
     * @param libretaUniversitaria el numero de la libreta universitaria del estudiante
     * @param nombre el nombre del estudiante
     * @param apellido el apellido del estudiante
     * @param genero el genero del estudiante
     * @param edad la edad del estudiante
     * @param ciudad la ciudad del estudiante
     */
    public Estudiante(Long dni, Long libretaUniversitaria, String nombre, String apellido, String genero,int edad, String ciudad) {
        this.dni = dni;
        this.libretaUniversitaria = libretaUniversitaria;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad=edad;
        this.ciudad = ciudad;
        this.carreras=new ArrayList<>();
        assert ( this.edad<0 || this.edad>=100): "se a dado una edad absurda";
     }

    /**
     * Metodo que devuelve el dni del estudiante
     * @return retorna el dni
     */
    public Long getDni() {
        return dni;
    }

    /**
     * Metodo que devuelve la libreta universitaria del estudiante
     * @return devuelve un Long, la libreta universitaria
     */
    public Long getLibretaUniversitaria() {
        return libretaUniversitaria;
    }

    /**
     * Metodo que devuelve el nombre del estudiante
     * @return retorna un string con el nombre del estudiante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que devuelve el apellido del estudiante
     * @return retorna un string con el apellido del estudiante
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Metodo que retorna el género del estudiante
     * @return devuelve un string con el género del estudiante
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Metodo que retorna la edad del estudiante
     * @return devuelve un int con la edad del estudiante
     */
    public int getEdad(){
        return this.edad;
    }

    /**
     * Metodo que devuelve la ciudad de donde proviene el estudiante
     * @return retorna la ciudad del estudiante
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Metodo que agrega la carrera en la cual se inscribio el estudiante
     * @param carrera es un objeto Carrera_Estudiante que guarda
     *                la relacion del estudiante con la carrera
     */
    public void addCarrera(Carrera_Estudiante carrera){
        this.carreras.add(carrera);
    }

    /**
     * Metodo para setear el nombre del estudiante
     * @param nombre nuevo nombre que pisara al antiguo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para cambiar el apellido por uno nuevo
     * @param apellido nuevo apellido que pisara al antiguo
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Metodo para setear el genero del estudiante por uno nuevo
     * @param genero nuevo genero que pisara al antiguo
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Metodo para setear la edad del estudiante
     * @param e es la nueva edad que pisara a la antigua
     */
    public void setEdad(int e){
        this.edad=e;
    }

    /**
     * Metodo para setear la ciudad del estudiante
     * @param ciudad es la nueva ciudad en la que ahora reside el estudiante
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Metodo para imprimir al estudiante con todos sus atributos
     * @return retorna un string con todos los datos del estudiante
     */
    @Override
    public String toString() {
        return "Estudiante{" +
                "dni=" + dni +
                ", lU=" + libretaUniversitaria +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero='" + genero + '\'' +
                ", edad=" + edad +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
