package com.example.integrador2.factories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase encargada de proveer la conexion a la base de datos derby utilizando
 * Jpa, o sea devolviendo el EntityManager para realizar todas las transacciones
 * que se hagan con la base de datos derby
 * @version 25/4/2024 A.0
 */
public class FactoryDerby extends FactoryGeneral{
    private static FactoryDerby instance = null;
    public static String nombreUnidad="baseDeDatosDerby";
    private EntityManager entManager=null;
    private EntityManagerFactory entManagerFactory=null;

    /**
     * Constructor vacio para invocar a un objeto de estos sin atributos
     */
    public FactoryDerby() {
    }

    /**
     * Constructor que recibe al entity manager y al factory
     * @param entManager la entity manager provista por jpa
     * @param entManagerFactory la entity manager factory provista por jpa
     */
    public FactoryDerby(EntityManager entManager, EntityManagerFactory entManagerFactory) {
        this.entManager = entManager;
        this.entManagerFactory = entManagerFactory;
    }

    /**
     * Metodo que devuelve una instancia del Factory Derby con su Entity Manger y Entity Manager Factory
     * @return retorna un objeto Factory Derby
     */
    public static synchronized FactoryDerby getEntityManagerFactory() {
        if (instance == null) {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory(FactoryDerby.nombreUnidad);
            EntityManager em=emf.createEntityManager();
            instance = new FactoryDerby(em,emf);


        }
        return instance;
    }
}
