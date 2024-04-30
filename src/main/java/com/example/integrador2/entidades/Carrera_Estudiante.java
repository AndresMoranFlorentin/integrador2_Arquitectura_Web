package com.example.integrador2.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Clase encargada de persistir en la base de datos a la entidad Carrera_Estudiante
 * que guarda la relacion entre el estudiante y la carrera, y sus atributos y metodos relacionados
 * @version 25/4/2024
 */
@Entity
public class Carrera_Estudiante implements Serializable {
    @Id
    private Long id;
    @Id
    private Long dni;
    @Id
    private Long id_carrera;
    @Column
    private Integer fecha_inscripcion;
    @Column
    private Integer fecha_graduacion;
    @Column
    private int antiguedad;
    @ManyToOne
    @JoinColumn(name = "fk_carrera")
    private Carrera carrera;
    @ManyToOne
    @JoinColumn(name = "fk_estudiante")
    private Estudiante estudiante;

    /**
     * Construtor vacio para asi invocar a la entidad sin cargarle datos
     */
    public Carrera_Estudiante() {
    }

    /**
     * Constructor que recibe los parametros para establecer la union entre la
     *  Carrera y el Estudiante
     * @param id el identificador del registro
     * @param dni el dni del estudiante
     * @param id_carrera el id de la carrera en la que se inscribira
     * @param fecha_inscripcion la fecha en la que se inscribio el estudiante
     * @param graduacion la fecha en la que se graduo, puede ser nulla
     * @param antiguedad y la antiguedad desde que se inscribio el estudiante a la carrera
     */
    public Carrera_Estudiante(Long id,Long dni,Long id_carrera, Integer fecha_inscripcion,Integer graduacion,int antiguedad) {
        this.id=id;
        this.dni = dni;
        this.id_carrera = id_carrera;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_graduacion=graduacion;
        this.antiguedad=antiguedad;
    }


    /**
     * Metodo para agregar en caso de que no tenga todavia fecha, la graduacion del estudiante
     * @param fecha fecha de la graduacion del alumno
     * @param dni el dni del estudiante que se graduo de la carrera
     * @param id_carrera el id de la carrera en la cual se graduo el estudiante
     */
    public void addFechaGraduacion(int fecha, int dni, int id_carrera){
        if(this.fecha_graduacion==0) {
            if (this.fecha_inscripcion<fecha) {
                    this.fecha_graduacion = fecha;
            }
        }
    }

    /**
     * Metodo para devolver el identificador del registro
     * @return devuelve un Long con el identificador del registro
     */
    public Long getId(){
        return this.id;
    }

    /**
     * Metodo para devolver el dni del estudiante que se inscribio en el registro
     * @return retorna un Long del dni del estudiante
     */
    public Long getDni() {
        return dni;
    }

    /**
     * Metodo que devuelve el identificador de la carrera del registro
     * @return devuelve un Long del identificador de la carrera
     */
    public Long getId_carrera() {
        return id_carrera;
    }

    /**
     * Metodo que devuelve la fecha de cuando el estudiante se inscribio
     * @return devuelve la fecha de inscripcion del estudiante
     */
    public int getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    /**
     * Metodo que devuelve la fecha de graduacion del estudiante
     * @return puede devolver una fecha o un null
     */
    public int getFecha_graduacion() {
        return fecha_graduacion;
    }

}
