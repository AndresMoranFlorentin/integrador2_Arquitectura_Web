package com.example.integrador2.ayudantes;

import com.example.integrador2.entidades.Carrera;

import com.example.integrador2.entidades.Carrera_Estudiante;
import com.example.integrador2.entidades.Estudiante;
import jakarta.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Esta es una clase hecha para subir los datos de csv dados a traves de direcciones de csv
 * @version 25/4/2024 A.0
 */
public class SubirCsv {
    private EntityManager eManager;

    /**
     *
     * @param em es el entity manager o sea el que gestiona las transacciones de la
     *           base de datos con los objetos-entidades
     */
    public SubirCsv(EntityManager em) {
        this.eManager = em;
    }

    /**
     * Metodo encargado de leer el archivo csv de Carreras y transformarlos en
     * una lista de objetos Carrera
     * @param url es la direccion dentro del proyecto donde está guardado el archivo csv
     * @throws IOException clase encargada de recibir el error que pueda suceder con las
     * clases buferedReader y FileReader
     */
    public void insertarCarreras(String url) throws IOException {
        eManager.getTransaction().begin();
        BufferedReader lector = new BufferedReader(new FileReader(url));
        String linea = "";
        String filas[] = null;
        while ((linea = lector.readLine()) != null) {
            filas = linea.split(",");
            if (!filas[1].equals("carrera")) {//este control es porque en la primera fila se encuentra los nombre de las columnas
                Long id = Long.parseLong(filas[0]);
                String nombre = filas[1];
                int duracion = Integer.parseInt(filas[2]);
                Carrera nueva = new Carrera(id, nombre, duracion);
                eManager.persist(nueva);
            }
        }
        eManager.getTransaction().commit();
     }

    /**
     * Metodo encargado de leer el archivo csv para Estudiantes y transformarlos en objetos Estudiantes y subirlos
     * a la base de datos
     * @param url direccion de donde se encuentra el csv de Estudiantes
     * @throws IOException receptor de los errores que puedan surgir de las clases Bufered Reader y FileReader
     */
    public void insertarEstudiantes(String url) throws IOException {
        eManager.getTransaction().begin();
        BufferedReader lector = new BufferedReader(new FileReader(url));
        String linea = "";
        String filas[] = null;
        while ((linea = lector.readLine()) != null) {
            filas = linea.split(",");
            if (!filas[1].equals("nombre")) {//este control es porque en la primera fila se encuentra los nombre de las columnas
                Long dni = Long.parseLong(filas[0]);
                String nombre = filas[1];
                String apellido=filas[2];
                int edad=Integer.parseInt(filas[3]);
                String genero=filas[4];
                String ciudad=filas[5];
                Long libreUni=Long.parseLong(filas[6]);
                Estudiante estudiante=new Estudiante(dni,libreUni,nombre,apellido,genero,edad,ciudad);
                 eManager.persist(estudiante);
            }
        }
        eManager.getTransaction().commit();
     }

    /**
     * Metodo encargado de leer los datos del csv CarreraEstudiante, o sea los que guardan
     * la relacion Carrera y Estudiante y transformarlos en objetos que son subidos a la base de datos
     * @param urlEstuCarre direccion de donde está el csv de Carrera_Estudiante
     * @throws IOException receptor de los errores que puedan surgir de las clases Bufered Reader y FileReader
     */
    public void insertarCarreraEstudiante(String urlEstuCarre) throws IOException {
        eManager.getTransaction().begin();
        BufferedReader lector = new BufferedReader(new FileReader(urlEstuCarre));

        String linea = "";
        String filas[] = null;
        while ((linea = lector.readLine()) != null) {
            filas = linea.split(",");
            if (!filas[0].equals("id")) {//este control es porque en la primera fila se encuentra los nombre de las columnas
                Long id = Long.parseLong(filas[0]);
                Long dni=Long.parseLong(filas[1]);
                Long idCarrera=Long.parseLong(filas[2]);
                int añoInscripcion = Integer.parseInt(filas[3]);
                int añoGraduacion = Integer.parseInt(filas[4]);
                int antiguedad = Integer.parseInt(filas[5]);

                //LocalDate inscripcion = LocalDate.ofYearDay(añoInscripcion, 1); // El primer día del año de inscripción
               // LocalDate graduacion = LocalDate.ofYearDay(añoGraduacion, 1); // El primer día del año de graduación
                if(añoGraduacion==0){
                   añoGraduacion=0;
                }
                Carrera_Estudiante carreEstu = new Carrera_Estudiante(id, dni, idCarrera, añoInscripcion, añoGraduacion, antiguedad);

                eManager.persist(carreEstu);
            }
        }
        eManager.getTransaction().commit();
     }
}
