package com.example.integrador2;

import com.example.integrador2.ayudantes.SubirCsv;
import com.example.integrador2.dto.CarreraDto;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;
import com.example.integrador2.factories.FactoryGeneral;
import com.example.integrador2.factories.FactoryMySQL;
import com.example.integrador2.repositorios.RepositorioCarrera;
import com.example.integrador2.repositorios.RepositorioCarreraEstudiante;
import com.example.integrador2.repositorios.RepositorioEstudiante;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        FactoryMySQL mysql = (FactoryMySQL) FactoryGeneral.getFactory(2);
        String urlEstudiantes="./src/main/resources/csv/estudiantes.csv";
        String urlCarreras="./src/main/resources/csv/carreras.csv";
        String urlCarreEstu="./src/main/resources/csv/estudianteCarrera.csv";
        /**
         * llamo a un ayudante y le doy las url de los csv para que los cargue a
         * la base de datos, primero las carreras luego los estudiantes y finalmente las carrera_estudiantes
         */
        SubirCsv subir_csv=new SubirCsv(mysql.getEntManager());
        subir_csv.insertarCarreras(urlCarreras);
        subir_csv.insertarEstudiantes(urlEstudiantes);
        subir_csv.insertarCarreraEstudiante(urlCarreEstu);
        /**
         * consigna 1) dar de alta un estudiante
         */
        Estudiante nuevoE=new Estudiante(44830983L,1983L,"Ramiro","Velazques","Male",24,"Azul");
        RepositorioEstudiante rpEstu=mysql.getEstudiante();
        rpEstu.darDeAltaEstudiante(nuevoE);
        /**
         * consigna 2) matricular un estudiante en una carrera
         */
        RepositorioCarrera repCarre=mysql.getCarrera();
        RepositorioCarreraEstudiante repCarreEstu=mysql.getCarreraEstudiante();
        Carrera carreraX=repCarre.getCarrera(1L);
        Estudiante estuX=rpEstu.getEstudiantePorNumLibreta(1983L);
        repCarreEstu.matricularEstudiante(estuX,carreraX);
        /**
         * Consigna 3) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
         */

        /**
         * Consigna 4) recuperar un estudiante, con base en su número de libreta universitaria.
         */
        Long lU=19844L;
        Estudiante estuZ=rpEstu.getEstudiantePorNumLibreta(19844L);
        System.out.println(" El estudiante que se busco es el que tiene libreta universitaria "+lU+"L,:");
        System.out.println(estuZ.toString());
        System.out.println("-----------------------------------------------");
        /**
         * Consigna 5) recuperar todos los estudiantes, en base a su género.
         */
        String genero="Female";
        List<Estudiante> listaEstudiantesMujeres=rpEstu.getEstudiantePorGenero(genero);
        System.out.println("lista de los estudiantes del genero: "+genero);
        imprimirLista(listaEstudiantesMujeres);
        /**
         * Consigna 6) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
         */
        //List<CarreraDto> carrerasInscriptos=repCarre.getCarrerasConInscriptos();
        //Iterator<CarreraDto> it=carrerasInscriptos.iterator();
        //while ()
        System.out.println("hasta aca funciona");

    }

    private static void imprimirLista(List<Estudiante> lista) {
        Iterator<Estudiante> it=lista.iterator();
        while (it.hasNext()){
            Estudiante nuevo=it.next();
            System.out.println(nuevo.toString());
        }
        System.out.println();
    }
}

