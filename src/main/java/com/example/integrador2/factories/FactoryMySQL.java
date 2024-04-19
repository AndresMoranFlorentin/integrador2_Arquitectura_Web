package com.example.integrador2.factories;

import com.example.integrador2.repositorios.RepositorioCarrera;
import com.example.integrador2.repositorios.RepositorioCarreraEstudiante;
import com.example.integrador2.repositorios.RepositorioEstudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FactoryMySQL extends FactoryGeneral {
    private static FactoryMySQL instance = null;
    private static String nombreUnidad="baseDeDatosMySQL";
    private EntityManager entManager=null;
    private EntityManagerFactory entManagerFactory=null;
    public FactoryMySQL() {
    }
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("baseDeDatosMySQL");
    public void closeConexion(){
        emf.close();
    }
    public RepositorioEstudiante getEstudiante(){
        return new RepositorioEstudiante();
    }
    public RepositorioCarrera getCarrera(){
        return new RepositorioCarrera();
    }
    public RepositorioCarreraEstudiante getCarreraEstudiante(){
        return new RepositorioCarreraEstudiante();
    }
    /*public FactoryMySQL(EntityManager entManager, EntityManagerFactory entManagerFactory) {
        this.entManager = entManager;
        this.entManagerFactory = entManagerFactory;
    }

    public static synchronized FactoryMySQL getEntityManagerFactory() {
        if (instance == null) {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory(FactoryMySQL.nombreUnidad);
            EntityManager em=emf.createEntityManager();
            instance = new FactoryMySQL(em,emf);


        }
        return instance;
    }

    public EntityManager getEntManager() {
        return entManager;
    }

    public EntityManagerFactory getEntManagerFactory() {
        return entManagerFactory;
    }*/
}
