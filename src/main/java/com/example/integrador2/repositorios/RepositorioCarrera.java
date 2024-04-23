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
        String jpql="SELECT c.id_carrera,c.nombre,c.duracion, COUNT(c.duracion) AS cant_inscriptos " +
                "FROM Carrera AS c " +
                "JOIN Carrera_Estudiante AS ce " +
                "WHERE (c.id_carrera=ce.id_carrera) " +
                "GROUP BY (c.id_carrera) " +
                "ORDER BY cant_inscriptos DESC";
        Query query= em.createQuery(jpql);
        List<Object[]> resultado=query.getResultList();
        for(Object[]r: resultado) {
            Long id = (Long) r[0];
            String nombre = (String) r[1];
            int duracion = (int) r[2];
            Long inscriptos = (Long) r[3];
            Integer insc=Integer.parseInt(String.valueOf(inscriptos));
            carreras.add(new CarreraDto(id,nombre,duracion,insc));
        }
        em.getTransaction().commit();

        return carreras;
    }
    /*public List<Carrera> getCarrerasConInscriptos() {
        em.getTransaction().begin();
        List<Carrera> carreras=new ArrayList<Carrera>();
        String jpql="SELECT c.id_carrera,c.nombre,c.duracion, COUNT(c.id_carrera) AS cant_inscriptos " +
                "FROM Carrera AS c " +
                "JOIN Carrera_Estudiante AS ce " +
                "WHERE (c.id_carrera=ce.id_carrera) " +
                "GROUP BY (c.id_carrera) " +
                "ORDER BY cant_inscriptos DESC";
        Query query= em.createQuery(jpql);
        Iterator<Carrera> it=query.getResultList().iterator();
        while(it.hasNext()){
            Carrera nuevo=it.next();
            Long id=nuevo.getId_carrera();
            String nombre=nuevo.getNombre();
            int duracion=nuevo.getDuracion();
            System.out.println("id: "+id+", nombre: "+nombre);
//            Long cantInscriptos=nuevo.getCant_inscriptos();
//            System.out.println("id: "+id+", nombre: "+nombre+", inscriptos: "+cantInscriptos);
//            carreras.add(new CarreraDto(id,nombre,duracion,cantInscriptos));
        }
        em.getTransaction().commit();

        return carreras;
    }*/
    //             Object[] result = (Object[]) it.next();
//            Long id = (Long) result[0];
//            String nombre = (String) result[1];
//            int duracion = (int) result[2];
//            Long inscriptos=(Long) result[3];
//                System.out.println("ID: " + id + ", Nombre: " + nombre + ", duracion: " + duracion+", inscriptos: "+inscriptos);
 /*   String jpql="SELECT c.id_carrera,c.nombre,c.duracion, COUNT(c.id_carrera) AS cant_inscriptos " +
            "FROM Carrera AS c " +
            "JOIN Carrera_Estudiante AS ce " +
            "WHERE (c.id_carrera=ce.id_carrera) " +
            "GROUP BY (c.id_carrera) " +
            "ORDER BY cant_inscriptos DESC";*/
}
