package com.example.integrador2.repositorios;

import com.example.integrador2.dto.ReporteTdo;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.dto.CarreraDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase encargada de ejecutar las operaciones de baja alta modificacion
 * de la Entidad Carrera y que estos cambios se persistan
 * @version 25/4/2024 A.0
 */
public class RepositorioCarrera implements RepoCarreraInt {
    private EntityManager em=null;

    /**
     * Constructor vacio, se crea la clase con todos sus atributos nulos
     */
    public RepositorioCarrera(){

    }

    /**
     * Constructor que recibe al entity manager que le permitira interactuar con la base de datos
     * @param em objeto Entity Manager
     */
    public RepositorioCarrera(EntityManager em){
        this.em=em;
    }
    /**
     * Metodo para borrar una carrera de la base de datos
     * @param carrera recibe la carrera que desea eliminar el usuario
     */
    @Override
    public void delete(Carrera carrera) {
       // em.remove(carrera);
    }

    /**
     * Metodo para pedir una carrera en especifico
     * @param id_carrera  es un identificador de la carrera que se busca
     * @return retorna una Carrera
     */
    @Override
    public Carrera getCarrera(Long id_carrera) {
        em.getTransaction().begin();
        Carrera carrera=em.find(Carrera.class,id_carrera);
        em.getTransaction().commit();
        return carrera;
    }
    /**
     * Metodo encargado de devolver una lista de las carreras
     * que tienen inscriptos y cuantos son
     * @return devuelve una lista de objetos CarreraDto, que tienen un atributo mas que
     * la entidad Carrera y es la cantidad de inscriptos
     * @see CarreraDto
     */
    @Override
    public List<CarreraDto> getCarrerasConInscriptos() {
        em.getTransaction().begin();
        List<CarreraDto> carreras=new ArrayList<CarreraDto>();
        String jpql="SELECT c.id_carrera,c.nombre,c.duracion, COUNT(c.duracion) AS cant_inscriptos " +
                "FROM Carrera AS c " +
                "JOIN Carrera_Estudiante AS ce " +
                "WHERE (c.id_carrera=ce.id_carrera) " +
                "GROUP BY (c.id_carrera) " +
                "ORDER BY cant_inscriptos DESC";
        Query query= em.createQuery(jpql);
        List<Object[]> resultado=query.getResultList();
        for(Object[]r: resultado) {
            Long id = (Long) r[0];
            String nombre = (String) r[1];
            int duracion = (int) r[2];
            Long inscriptos = (Long) r[3];
            Integer insc=Integer.parseInt(String.valueOf(inscriptos));
            carreras.add(new CarreraDto(id,nombre,duracion,insc));
        }
        em.getTransaction().commit();
        return carreras;
    }

   /*  public List<ReporteTdo> getCarrerasConInscriptosEgresados1() {
        em.getTransaction().begin();
        List<ReporteTdo> lista = new ArrayList<>();

        // Consulta para carreras con inscriptos pero sin graduados
        String jpqlInscriptos = "SELECT c.nombre, ce.fecha_inscripcion AS anio, COUNT(ce.fecha_inscripcion) AS inscriptos, 0 AS graduados " +
                "FROM Carrera c " +
                "INNER JOIN Carrera_Estudiante ce ON c.id_carrera = ce.id_carrera " +
                "GROUP BY c.nombre, ce.fecha_inscripcion " +
                "HAVING inscriptos != 0";

        // Consulta para carreras con graduados pero sin inscriptos
        String jpqlGraduados = "SELECT c.nombre, ce.fecha_graduacion, 0 AS inscriptos, COUNT(ce.fecha_graduacion) AS graduados " +
                "FROM Carrera c " +
                "INNER JOIN Carrera_Estudiante ce ON c.id_carrera = ce.id_carrera " +
                "GROUP BY c.nombre, ce.fecha_graduacion " +
                "HAVING graduados != 0";

        Query queryInscriptos = em.createNativeQuery(jpqlInscriptos);
        Query queryGraduados = em.createNativeQuery(jpqlGraduados);

        List<Object[]> resultadoInscriptos = queryInscriptos.getResultList();
        List<Object[]> resultadoGraduados = queryGraduados.getResultList();

        // Combinar resultados
        for (Object[] inscriptos : resultadoInscriptos) {
            String nombre = (String) inscriptos[0];
            Integer anio = (Integer) inscriptos[1];
            Long inscriptosCount = (Long) inscriptos[2];

            lista.add(new ReporteTdo(nombre, anio, inscriptosCount, 0L));
        }

        for (Object[] graduados : resultadoGraduados) {
            String nombre = (String) graduados[0];
            int anio = (int) graduados[1];
            long graduadosCount = (long) graduados[3];

            // Buscar si ya existe la carrera en la lista
            boolean carreraEncontrada = false;
            for (ReporteTdo reporte : lista) {
                if (reporte.getNombre().equals(nombre) && reporte.getAnio() == anio) {
                    reporte.setGraduados(graduadosCount);
                    carreraEncontrada = true;
                    break;
                }
            }

            // Si la carrera no está en la lista, añadirla
            if (!carreraEncontrada) {
                lista.add(new ReporteTdo(nombre, anio, 0L, graduadosCount));
            }
        }

        em.getTransaction().commit();
        return lista;
    }*/
    @Override
    public List<ReporteTdo> getCarrerasConInscriptosEgresados() {
        em.getTransaction().begin();
        LinkedList<ReporteTdo> lista=new LinkedList<ReporteTdo>();
        String jpqlq="SELECT c.nombre, ce.fecha_inscripcion AS anio, COUNT(ce.fecha_inscripcion) AS inscriptos, 0 AS graduados " +
                "FROM Carrera c " +
                "INNER JOIN Carrera_Estudiante ce ON c.id_carrera = ce.id_carrera " +
                "GROUP BY c.nombre, ce.fecha_inscripcion " +
                "HAVING inscriptos != 0 " +
                "UNION " +
                "SELECT c1.nombre, ce.fecha_graduacion, 0 AS inscriptos, COUNT(ce.fecha_graduacion) AS graduados " +
                "FROM Carrera c1 " +
                "INNER JOIN Carrera_Estudiante ce ON c1.id_carrera = ce.id_carrera " +
                "GROUP BY c1.nombre, ce.fecha_graduacion " +
                "HAVING graduados != 0 " +
                 "ORDER BY nombre, anio ASC;";
        Query query=em.createNativeQuery(jpqlq);
        List<Object[]> resultado=query.getResultList();
        for(Object[]r: resultado) {
            String nombre = (String) r[0];
            Integer fecha = (Integer) r[1];
            Long inscriptos = (Long) r[2];
            Long graduados = (Long) r[3];
           // System.out.println("nombre: "+nombre+", fecha: "+fecha+", inscriptos: "+inscriptos+", graduados: "+graduados);
            ReporteTdo nuevo=new ReporteTdo(nombre,fecha,inscriptos,graduados);
             lista.addLast(nuevo);
        }
        em.getTransaction().commit();
        return lista;
    }

}
