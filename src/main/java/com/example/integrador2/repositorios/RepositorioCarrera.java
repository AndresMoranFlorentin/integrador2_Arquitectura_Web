package com.example.integrador2.repositorios;

import com.example.integrador2.dto.CarreraDto;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

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
    public Carrera getCarrera(Long id_carrera) {
        em.getTransaction().begin();
        Carrera carrera=em.find(Carrera.class,id_carrera);
        em.getTransaction().commit();
        return carrera;
    }

    @Override
    public List<CarreraDto> getCarrerasConInscriptos() {
        em.getTransaction().begin();
        List<CarreraDto> carreras=new ArrayList<>();
        String jpql="SELECT c, COUNT(*) AS inscriptos FROM Carrera c" +
                " JOIN Carrera_Estudiante ce" +
                " WHERE (c.id=ce.id_carrera)" +
                " GROUP BY(c.id)" +
                " ORDER BY DESC(inscriptos)";
        Query query= em.createQuery(jpql);
        carreras=query.getResultList();
        em.getTransaction().commit();
        return carreras;
    }
}
