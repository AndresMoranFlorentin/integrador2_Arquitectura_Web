package com.example.integrador2.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
@Entity
public class Carrera_Estudiante implements Serializable {
    @Id
    private Long id;
    @Id
    private Long dni;
    @Id
    private Long id_carrera;
    @Column
    private LocalDate fecha_inscripcion;
    @Column
    private LocalDate fecha_graduacion;
    @Column
    private int antiguedad;
    @ManyToOne
    @JoinColumn(name = "fk_carrera")
    private Carrera carrera;
    @ManyToOne
    @JoinColumn(name = "fk_estudiante")
    private Estudiante estudiante;

    public Carrera_Estudiante() {
    }

    public Carrera_Estudiante(Long id,Long dni,Long id_carrera, LocalDate fecha_inscripcion,LocalDate graduacion,int antiguedad) {
        this.id=id;
        this.dni = dni;
        this.id_carrera = id_carrera;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_graduacion=graduacion;
        this.antiguedad=antiguedad;
    }


    /**
     *
     * @param fecha fecha de la graduacion del alumno
     * @param dni el dni del estudiante que se graduo de la carrera
     * @param id_carrera el id de la carrera en la cual se graduo el estudiante
     */
    public void addFechaGraduacion(LocalDate fecha, int dni, int id_carrera){

        if(this.fecha_inscripcion.compareTo(fecha)<0){
            /**
             * esta variable guardara los años que pasaron desde la fecha que se inscribio
             */
          int aniosG=fecha.getYear();//dice que se inscribio en 2018 por ejemplo
            /** esta variable guarda en que año se inscribio*/
          int aniosI= fecha_inscripcion.getYear();
            /**
             * carreraX guardaria la duracion de la carrera en que se inscribio
             */
          int carreraX=4;
          if(aniosG-aniosI >=carreraX ){
              this.fecha_graduacion=fecha;
          }
        }
    }
    public Long getId(){
        return this.id;
    }
    public Long getDni() {
        return dni;
    }

    public Long getId_carrera() {
        return id_carrera;
    }

    public LocalDate getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public LocalDate getFecha_graduacion() {
        return fecha_graduacion;
    }

}
