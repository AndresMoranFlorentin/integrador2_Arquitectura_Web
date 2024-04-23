package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEstudiante implements RepoEstudianteInt {
    private EntityManager em=null;
    public RepositorioEstudiante(){

    }
    public RepositorioEstudiante(EntityManager em){

        this.em=em;
    }
    @Override
    public Estudiante getEstudiantePorNumLibreta(Long id) {
        em.getTransaction().begin();
        String jpql="SELECT e FROM Estudiante e WHERE (e.libretaUniversitaria=?1)";
        Query query=em.createQuery(jpql);
        query.setParameter(1,id);
        Estudiante elegido= (Estudiante) query.getSingleResult();
        em.getTransaction().commit();
        return elegido;
    }

    @Override
    public List<Estudiante> getEstudiantePorGenero(String genero) {
        List<Estudiante> estudiantes=new ArrayList<>();
        String jpql="SELECT e FROM Estudiante e WHERE e.genero=:generoElegido";
        Query query= em.createQuery(jpql);
        query.setParameter("generoElegido",genero);
        estudiantes=query.getResultList();

        return estudiantes;
    }

    @Override
    public List<Estudiante> getEstudiantePorCarrera(Carrera c, String ciudad) {
        em.getTransaction().begin();
        List<Estudiante> estudiantes=new ArrayList<>();
        Long idC=c.getId_carrera();
        String jpql="SELECT e FROM Estudiante e " +
                    "JOIN Carrera_Estudiante ce JOIN Carrera c " +
                    "WHERE (e.dni=ce.dni) " +
                    "AND (e.ciudad=?1) " +
                    "AND (ce.id_carrera=?2)";
        Query query=em.createQuery(jpql);
        query.setParameter(1,ciudad);
        query.setParameter(2,idC);
        estudiantes=query.getResultList();
        em.getTransaction().commit();
        return estudiantes;
    }

    @Override
    public void darDeAltaEstudiante(Estudiante estu) {
      em.getTransaction().begin();
      em.persist(estu);
      em.getTransaction().commit();
    }

    @Override
    public void deleteEstudiante(Estudiante estu) {
        em.getTransaction().begin();
        em.remove(estu);
        em.getTransaction().commit();
    }
}
