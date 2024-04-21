package com.example.integrador2.ayudantes;

import com.example.integrador2.entidades.Carrera;

import com.example.integrador2.entidades.Carrera_Estudiante;
import com.example.integrador2.entidades.Estudiante;
import jakarta.persistence.EntityManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubirCsv {
    private EntityManager eManager;

    public SubirCsv(EntityManager em) {
        this.eManager = em;
    }

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
       // eManager.close();
    }

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
      //  eManager.close();
    }

    public void insertarCarreraEstudiante(String urlEstuCarre) throws IOException, ParseException {
        eManager.getTransaction().begin();
        BufferedReader lector = new BufferedReader(new FileReader(urlEstuCarre));
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy"); // Formato solo para el año

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
                Date inscripcion = formatoFecha.parse(String.valueOf(añoInscripcion)); // Convierte el año en un objeto Date
                Date graduacion = formatoFecha.parse(String.valueOf(añoGraduacion)); // Convierte el año en un objeto Date
                int antiguedad=Integer.parseInt(filas[5]);

                Carrera_Estudiante carreEstu=new Carrera_Estudiante(id,dni,idCarrera,inscripcion,graduacion,antiguedad);

                eManager.persist(carreEstu);
            }
        }
        eManager.getTransaction().commit();
      //  eManager.close();
    }
}
