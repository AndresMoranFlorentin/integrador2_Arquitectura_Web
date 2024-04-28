package com.example.integrador2.factories;

import com.example.integrador2.repositorios.RepositorioCarrera;
import com.example.integrador2.repositorios.RepositorioCarreraEstudiante;
import com.example.integrador2.repositorios.RepositorioEstudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase que tiene su entity manager y el factory pero para realizar la conexion a la
 * base de datos de MySql y retorna los repo o daos de las distintas entidades
 * @version 25/4/2024 A.0
 * @see RepositorioCarrera, {@link RepositorioEstudiante},{@link RepositorioCarreraEstudiante}
 */
public class FactoryMySQL extends FactoryGeneral {
    private static FactoryMySQL instance = null;
    private static String nombreUnidad="baseDeDatosMySQL";
    private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("baseDeDatosMySQL");
    private static EntityManager entManager=emf.createEntityManager();

    private EntityManagerFactory entManagerFactory=null;

    /**
     * Constructor vacio permite llamar a la entidad que ya tiene previamente cargado
     * atributos estaticos y acceder a ellos
     */
    public FactoryMySQL() {
    }

    /**
     * Metodo que cierra la conexion del EntityManagerFactory
     */
    public void closeConexion(){
        emf.close();
    }

    /**
     * Metodo que devuelve al que realizara la conexion con la entidad Estudiante y
     * las consultas que se le haran
     * @return devuelve un RepositorioEstudiante
     */
    public RepositorioEstudiante getEstudiante(){
        return new RepositorioEstudiante(entManager);
    }

    /**
     * Metodo que devuelve al que realizara la conexion con la entidad Carrera y
     * las consultas que se le haran
     * @return devuelve un RepositorioCarrera
     */
    public RepositorioCarrera getCarrera(){
        return new RepositorioCarrera(entManager);
    }
    /**
     * Metodo que devuelve al que realizara la conexion con la entidad RepositorioCarreraEstudiante
     * quien guarda la relacion con el Estudiante y su carrera
     * @return devuelve un RepositorioCarreraEstudiante
     */
    public RepositorioCarreraEstudiante getCarreraEstudiante(){
        return new RepositorioCarreraEstudiante(entManager);
    }

    /**
     * Metodo Estatico que devuelve al entity manager de este factory
     * en caso de que otra clase lo use
     * @return devuelve un Entity Manager con conexion a MySql
     */
    public static EntityManager getEntManager() {
        return entManager;
    }


}
