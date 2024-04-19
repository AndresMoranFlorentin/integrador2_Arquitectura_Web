package com.example.integrador2.factories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FactoryDerby extends FactoryGeneral{
    private static FactoryDerby instance = null;
    public static String nombreUnidad="baseDeDatosDerby";
    private EntityManager entManager=null;
    private EntityManagerFactory entManagerFactory=null;
    public FactoryDerby() {
    }

    public FactoryDerby(EntityManager entManager, EntityManagerFactory entManagerFactory) {
        this.entManager = entManager;
        this.entManagerFactory = entManagerFactory;
    }

    public static synchronized FactoryDerby getEntityManagerFactory() {
        if (instance == null) {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory(FactoryDerby.nombreUnidad);
            EntityManager em=emf.createEntityManager();
            instance = new FactoryDerby(em,emf);


        }
        return instance;
    }
}
