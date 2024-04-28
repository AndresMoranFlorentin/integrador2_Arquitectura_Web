package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase encargada de ejecutar las operaciones de baja alta modificacion
 * de la Entidad Estudiante y que estos cambios se persistan
 * @version 25/4/2024 A.0
 */
public class RepositorioEstudiante implements RepoEstudianteInt {
    private EntityManager em=null;
    /**
     * Constructor vacio, se crea la clase con todos sus atributos nulos
     */
    public RepositorioEstudiante(){

    }
    /**
     * Constructor que recibe al entity manager que le permitira interactuar con la base de datos
     * @param em objeto Entity Manager
     */
    public RepositorioEstudiante(EntityManager em){

        this.em=em;
    }
    /**
     * Metodo que devuelve a un Estudiante elegido
     * @param id el identificador del Estudiante en este caso su numero de libreta universitario
     * @return retorna el Estudiante elegido si es que lo encontro
     */
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
    /**
     * Metodo que devuelve todos los estudiantes de un genero dado
     * @param genero es un String que filtrara por ese genero a los estudiante que traeran
     * @return devuelve una lista de Estudiantes por genero
     */
    @Override
    public List<Estudiante> getEstudiantePorGenero(String genero) {
        List<Estudiante> estudiantes=new ArrayList<>();
        String jpql="SELECT e FROM Estudiante e WHERE e.genero=:generoElegido";
        Query query= em.createQuery(jpql);
        query.setParameter("generoElegido",genero);
        estudiantes=query.getResultList();

        return estudiantes;
    }
    /**
     * Metodo que retorna a todos los estudiantes pero ordenados alfabeticamente por apellido
     * @return devuelve una lista de todos los estudiantes
     */
@Override
    public List<Estudiante> getEstudiantesPorOrdenDelApellido() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String jpql = "SELECT e FROM Estudiante e " +
                "ORDER BY e.apellido ASC";
        Query query= em.createQuery(jpql);
        estudiantes=query.getResultList();

        return estudiantes;
    }
    /**
     * Metodo que devuelve todos los estudiantes de una carrera y ciudad
     * @param c es un String que filtrara por ese carrera a los estudiante que traera
     * @param ciudad es un string que tambien filtrara a aquellos estudiantes que provengan de esa ciudad
     * @return devuelve una lista de Estudiantes por carrera y ciudad dados
     */
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
    /**
     * Metodo que permitira agregar un nuevo estudiante a la base de datos
     * @param estu es el Estudiante que se le dara de alta
     */
    @Override
    public void darDeAltaEstudiante(Estudiante estu) {
      em.getTransaction().begin();
      em.persist(estu);
      em.getTransaction().commit();
    }
    /**
     * Metodo para borrar a un estudiante
     * @param estu el estudiante que se borrara
     */
    @Override
    public void deleteEstudiante(Estudiante estu) {
        em.getTransaction().begin();
        em.remove(estu);
        em.getTransaction().commit();
    }
}
