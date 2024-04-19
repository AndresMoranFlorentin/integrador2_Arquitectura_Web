package com.example.integrador2.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity
public class Carrera_Estudiante implements Serializable {
    @Id
    private Long dni;
    @Id
    private Long id_carrera;
    @Column
    private Date fecha_inscripcion;
    @Column
    private Date fecha_graduacion;
    @ManyToOne
    @JoinColumn(name = "fk_carrera")
    private Carrera carrera;
    @ManyToOne
    @JoinColumn(name = "fk_estudiante")
    private Estudiante estudiante;

    public Carrera_Estudiante() {
    }

    public Carrera_Estudiante(Long dni, Long id_carrera, Date fecha_inscripcion) {
        this.dni = dni;
        this.id_carrera = id_carrera;
        this.fecha_inscripcion = fecha_inscripcion;
    }

    /**
     *
     * @param fecha fecha de la graduacion del alumno
     * @param dni el dni del estudiante que se graduo de la carrera
     * @param id_carrera el id de la carrera en la cual se graduo el estudiante
     */
    public void addFechaGraduacion(Date fecha, int dni, int id_carrera){

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

    public Long getDni() {
        return dni;
    }

    public Long getId_carrera() {
        return id_carrera;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public Date getFecha_graduacion() {
        return fecha_graduacion;
    }

}
