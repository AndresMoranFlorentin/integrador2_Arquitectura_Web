package com.example.integrador2.factories;

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
    public FactoryMySQL(EntityManager entManager, EntityManagerFactory entManagerFactory) {
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
    }
}
