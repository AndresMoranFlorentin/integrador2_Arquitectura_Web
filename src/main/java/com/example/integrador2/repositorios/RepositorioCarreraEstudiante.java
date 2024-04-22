package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Carrera_Estudiante;
import com.example.integrador2.entidades.Estudiante;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalDate.now;

public class RepositorioCarreraEstudiante implements RepoCarreraEstudianteInt{
    private EntityManager em=null;
    public RepositorioCarreraEstudiante(EntityManager em){
        this.em=em;
    }
    @Override
    public void matricularEstudiante(Estudiante estu, Carrera carrera) {
        em.getTransaction().begin();
       //variables que cargaran los datos para el nuevo registro de estudiante_carrera
        int hoy=LocalDate.now().getYear();
        Long id=110L;
        Long dni=estu.getDni();
        Long idCarre=carrera.getId_carrera();
        LocalDate fechaInscripcion= LocalDate.now();

        int antiguedad=0;
        //se crea el nuevo registro
        Carrera_Estudiante nuevoRegistro=new Carrera_Estudiante(id,dni,idCarre,fechaInscripcion,null,antiguedad);
        em.persist(nuevoRegistro);

        //se agrega el registro al estudiante y la carrera
        estu.addCarrera(nuevoRegistro);
        em.merge(estu);

        carrera.addEstudianteAlaCarrera(nuevoRegistro);
        em.merge(carrera);

        //ahora se suben los cambios y se persiste este nuevo registro

        em.getTransaction().commit();
    }
}
