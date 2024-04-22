package com.example.integrador2.repositorios;

import com.example.integrador2.dto.CarreraDto;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Iterator;
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
        List<CarreraDto> carreras=new ArrayList<CarreraDto>();
        String jpql="SELECT c.id_carrera,c.nombre,c.duracion, COUNT(c.id_carrera) AS inscriptos FROM Carrera AS c " +
                "JOIN Carrera_Estudiante AS ce " +
                "WHERE c.id_carrera=ce.id_carrera " +
                "GROUP BY (c.id_carrera) " +
                "ORDER BY inscriptos DESC";
        Query query= em.createQuery(jpql);
        Iterator<CarreraDto> it=query.getResultList().iterator();
        while(it.hasNext()){
            CarreraDto nuevo=it.next();
            Long id=nuevo.getId_carrera();
            String nombre=nuevo.getNombre();
            int duracion=nuevo.getDuracion();
            int cantInscriptos=nuevo.getCant_inscriptos();
            System.out.println(id+", "+nombre+", "+cantInscriptos);
//            carreras.add(new CarreraDto(id,nombre,duracion,cantInscriptos));
        }
        em.getTransaction().commit();

        return carreras;
    }
}
