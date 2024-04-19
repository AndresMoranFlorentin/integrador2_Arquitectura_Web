package com.example.integrador2;
import com.example.integrador2.factories.FactoryGeneral;
import com.example.integrador2.factories.FactoryMySQL;
import com.example.integrador2.repositorios.RepositorioEstudiante;

public class Main {
    public static void main(String[] args) {
        FactoryMySQL mysql = (FactoryMySQL) FactoryGeneral.getFactory(2);
        RepositorioEstudiante rpE=mysql.getEstudiante();

         System.out.println("hasta aca funciona");

    }
}
