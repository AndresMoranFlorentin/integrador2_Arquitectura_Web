package com.example.integrador2;

import com.example.integrador2.ayudantes.SubirCsv;
import com.example.integrador2.dto.CarreraDto;
import com.example.integrador2.dto.ReporteTdo;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;
import com.example.integrador2.factories.FactoryGeneral;
import com.example.integrador2.factories.FactoryMySQL;
import com.example.integrador2.repositorios.RepositorioCarrera;
import com.example.integrador2.repositorios.RepositorioCarreraEstudiante;
import com.example.integrador2.repositorios.RepositorioEstudiante;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

/**
 * Clase encargada de realizar las consultas y pedidos a las entidades y su base de datos
 */
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
//        subir_csv.insertarCarreras(urlCarreras);
//        subir_csv.insertarEstudiantes(urlEstudiantes);
//        subir_csv.insertarCarreraEstudiante(urlCarreEstu);
        /**
         * consigna 1) dar de alta un estudiante
         */
        Estudiante nuevoE=new Estudiante(44830983L,1983L,"Ramiro","Velazques","Male",24,"Azul");
        RepositorioEstudiante rpEstu=mysql.getEstudiante();
//        rpEstu.darDeAltaEstudiante(nuevoE);
//        System.out.println("El estudiante "+nuevoE.getNombre()+" "+nuevoE.getApellido()+", fue dado de alta");
//        System.out.println("--------------------------------------------------------------");
        /**
         * consigna 2) matricular un estudiante en una carrera
         */
        RepositorioCarrera repCarre=mysql.getCarrera();
//        RepositorioCarreraEstudiante repCarreEstu=mysql.getCarreraEstudiante();
//        Carrera carreraX=repCarre.getCarrera(1L);
//        Estudiante estuX=rpEstu.getEstudiantePorNumLibreta(1983L);
        Long id=110L;//-->este es el id de la tabla Carrera Estudiante
//        repCarreEstu.matricularEstudiante(estuX,carreraX,id);
//        System.out.println("Se matriculo con exito al estudiante "+estuX.getNombre()+" "+estuX.getApellido()+"" +
//                ", en la carrera : "+carreraX.getNombre());
//        System.out.println("----------------------------------------------------");
        /**
         * Consigna 3) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
         */
//        List<Estudiante> listaEstudiantesOrd=rpEstu.getEstudiantesPorOrdenDelApellido();
//        System.out.println("lista de los estudiantes ordenados alfabeticamente por apellido: ");
//        imprimirLista(listaEstudiantesOrd);
        System.out.println("------------------------------------------------");

        /**
         * Consigna 4) recuperar un estudiante, con base en su número de libreta universitaria.
         */
        Long lU=19844L;
//        Estudiante estuZ=rpEstu.getEstudiantePorNumLibreta(19844L);
//        System.out.println(" El estudiante que se busco es el que tiene libreta universitaria "+lU+"L,:");
//        System.out.println(estuZ.toString());
        System.out.println("-----------------------------------------------");
        /**
         * Consigna 5) recuperar todos los estudiantes, en base a su género.
         */
        String genero="Female";
        List<Estudiante> listaEstudiantesMujeres=rpEstu.getEstudiantePorGenero(genero);
//        System.out.println("lista de los estudiantes del genero: "+genero);
//        imprimirLista(listaEstudiantesMujeres);
        System.out.println("------------------------------------------------");
        /**
         * Consigna 6) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
         */
        List<CarreraDto> carrerasInscriptos=repCarre.getCarrerasConInscriptos();
        Iterator<CarreraDto> it=carrerasInscriptos.iterator();
        System.out.println("Estas son las carreras con estudiantes inscriptos ordenadas de mayor a menor: ");
        while (it.hasNext()){
            CarreraDto car=it.next();
            System.out.println(car.toString());
        }
        System.out.println("--------------------------------------------------");
        /**
         * Consigna 7) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
         */
        String ciudad="Rauch";
        Carrera c=repCarre.getCarrera(1L);
        System.out.println("todos los estudiantes de la ciudad : "+ciudad+", y carrera : "+c.getNombre());

        List<Estudiante> estudiantesCarreraX=rpEstu.getEstudiantePorCarrera(c,ciudad);
        Iterator<Estudiante> ite=estudiantesCarreraX.iterator();
        while(ite.hasNext()){
            System.out.println();
            Estudiante estudiante=ite.next();
            System.out.println(estudiante.toString());
        }
        System.out.println("--------------------------------------------------------");
        /*Generar un reporte de las carreras, que para cada carrera incluya
         información de los inscriptos y egresados por año. Se deben ordenar
          las carreras alfabéticamente, y presentar los años de manera cronológica. */

        List<ReporteTdo> listaReportes=repCarre.getCarrerasConInscriptosEgresados();

//        for (ReporteTdo r : listaReportes) {
//            System.out.println(r.toString());
//        }

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

