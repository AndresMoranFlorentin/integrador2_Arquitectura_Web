package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import javax.persistence.EntityManager;

public class RepositorioCarrera implements RepoCarreraInt {
    private EntityManager em=null;
    public RepositorioCarrera(){

    }
    public RepositorioCarrera(EntityManager em){
        this.em=em;
    }
    @Override
    public void delete(Carrera carrera) {
       // em.remove(carrera);
    }

    @Override
    public void matricularEstudiante(Estudiante estu, Carrera carrera) {

    }

    @Override
    public RepositorioCarrera getCarrera(Carrera carrera) {
        return null;
    }
}
