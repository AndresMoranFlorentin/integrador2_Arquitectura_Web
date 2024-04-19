package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Estudiante;

import javax.persistence.EntityManager;

public class RepositorioEstudiante implements RepoEstudianteInt {
    private EntityManager em=null;
    public RepositorioEstudiante(EntityManager em){
        this.em=em;
    }
    @Override
    public Estudiante getEstudiantePorNumLibreta(Estudiante estu) {
        return null;
    }

    @Override
    public void darDeAltaEstudiante(Estudiante estu) {

    }

    @Override
    public void deleteEstudiante(Estudiante estu) {

    }
}
