package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import java.util.List;

/**
 * Interface encargada de heredar comportamiento a los repo de Estudiante que haya
 * @version 25/4/2024 A.0
 */
public interface RepoEstudianteInt {
    /**
     * Metodo que devuelve a un Estudiante elegido
     * @param id el identificador del Estudiante en este caso su numero de libreta universitario
     * @return retorna el Estudiante elegido
     */
    Estudiante getEstudiantePorNumLibreta(Long id);

    /**
     * Metodo que devuelve todos los estudiantes de un genero dado
     * @param genero es un String que filtrara por ese genero a los estudiante que traeran
     * @return devuelve una lista de Estudiantes por genero
     */
    List<Estudiante> getEstudiantePorGenero(String genero);

    /**
     * Metodo que retorna a todos los estudiantes pero ordenados alfabeticamente por apellido
     * @return devuelve una lista de todos los estudiantes
     */
    List<Estudiante> getEstudiantesPorOrdenDelApellido();
    /**
     * Metodo que devuelve todos los estudiantes de una carrera y ciudad
     * @param c es un String que filtrara por ese carrera a los estudiante que traera
     * @param ciudad es un string que tambien filtrara a aquellos estudiantes que provengan de esa ciudad
     * @return devuelve una lista de Estudiantes por carrera y ciudad dados
     */
    List<Estudiante> getEstudiantePorCarrera(Carrera c, String ciudad);

    /**
     * Metodo que permitira agregar un nuevo estudiante a la base de datos
     * @param estu es el Estudiante que se le dara de alta
     */
    void darDeAltaEstudiante(Estudiante estu);

    /**
     * Metodo para borrar a un estudiante
     * @param estu el estudiante que se borrara
     */
    void deleteEstudiante(Estudiante estu);

}
