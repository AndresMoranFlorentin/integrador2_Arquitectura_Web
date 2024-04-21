package com.example.integrador2;

import com.example.integrador2.ayudantes.SubirCsv;
import com.example.integrador2.factories.FactoryGeneral;
import com.example.integrador2.factories.FactoryMySQL;
import com.example.integrador2.repositorios.RepositorioEstudiante;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        FactoryMySQL mysql = (FactoryMySQL) FactoryGeneral.getFactory(2);
        RepositorioEstudiante rpE=mysql.getEstudiante();
        String urlEstudiantes="./src/main/resources/csv/estudiantes.csv";
        String urlCarreras="./src/main/resources/csv/carreras.csv";
        String urlCarreEstu="./src/main/resources/csv/estudianteCarrera.csv";

        SubirCsv subircsv=new SubirCsv(mysql.getEntManager());
        subircsv.insertarCarreras(urlCarreras);
        subircsv.insertarEstudiantes(urlEstudiantes);
        subircsv.insertarCarreraEstudiante(urlCarreEstu);
        System.out.println("hasta aca funciona");

    }
}

