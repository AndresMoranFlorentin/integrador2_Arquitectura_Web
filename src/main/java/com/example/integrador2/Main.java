package com.example.integrador2;

import com.example.integrador2.factories.FactoryGeneral;
import com.example.integrador2.factories.FactoryMySQL;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        FactoryGeneral mysql = FactoryGeneral.getFactory(2);
        FactoryMySQL gestorSQL = (FactoryMySQL) FactoryGeneral.getFactory(2);

        EntityManager em=gestorSQL.getEntManager();
        em.close();

         System.out.println("hasta aca funciona");

    }
}
