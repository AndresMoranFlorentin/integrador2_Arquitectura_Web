package com.example.integrador2.repositorios;

import com.example.integrador2.dto.ReporteTdo;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.dto.CarreraDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public List<ReporteTdo> getCarrerasConInscriptosEgresados() {
        em.getTransaction().begin();
        List<ReporteTdo> lista=new ArrayList<ReporteTdo>();
        String subquery = "(SELECT DISTINCT ce1.fechaInscripcion AS anio FROM CarreraEstudiante ce1 " +
                "UNION ALL " +
                "SELECT DISTINCT ce2.fechaGraduacion AS anio FROM CarreraEstudiante ce2)";

        String jpql = "SELECT c.nombre, " +
                "ce.fecha_inscripcion AS anio_inscripcion, " +
                "COUNT(ce.dni) AS inscriptos, " +
                "SUM(CASE WHEN ce.fecha_graduacion IS NOT NULL THEN 1 ELSE 0 END) AS egresados " +
                "FROM Carrera c " +
                "LEFT JOIN c.estudiantes ce " +
                "GROUP BY c.nombre, ce.fecha_inscripcion " +
                "ORDER BY c.nombre ASC, ce.fecha_inscripcion ASC";
        /*SELECT c.nombre, a.anio AS anio_inscripcion,
COUNT(ce.dni) AS inscriptos,
SUM(CASE WHEN ce.fecha_graduacion IS NOT NULL THEN 1 ELSE 0 END) AS egresados
FROM Carrera AS c
LEFT JOIN c.estudiantes ce
LEFT JOIN (SELECT ce1.fecha_inscripcion AS anio FROM Carrera c1 JOIN Carrera_Estudiante ce1
 UNION   SELECT DISTINCT YEAR(ce2.fecha_graduacion) AS anio FROM Carrera c2 JOIN Carrera_Estudiante ce2) a ON YEAR(ce.fecha_inscripcion) = a.anio OR YEAR(ce.fecha_graduacion) = a.anio ,
GROUP BY c.nombre, a.anio
ORDER BY c.nombre ASC, a.anio*/
        String jpql2="SELECT c.nombre, ce.fecha_inscripcion," +
                "COUNT(ce.dni) , " +
                "SUM(CASE WHEN ce.fecha_graduacion IS NOT NULL THEN 1 ELSE 0 END) AS egresados " +
                "FROM Carrera AS c " +
                "JOIN Carrera_Estudiante AS ce " +
                "WHERE (c.id_carrera = ce.id_carrera) "+
                "GROUP BY c.nombre,ce.fecha_inscripcion "+
                "ORDER BY c.nombre,ce.fecha_inscripcion,egresados ASC ";
        Query query=em.createQuery(jpql);
        List<Object[]> resultado=query.getResultList();
        for(Object[]r: resultado) {
            String nombre = (String) r[0];
            Integer fecha = (Integer) r[1];
            Long inscriptos = (Long) r[2];
            Long graduados = (Long) r[3];
            System.out.println("nombre: "+nombre+", fecha: "+fecha+", inscriptos: "+inscriptos+", graduados: "+graduados);
//            ReporteTdo nuevo=new ReporteTdo(nombre,fecha,inscriptos,graduados);
//            lista.add(nuevo);
        }
        em.getTransaction().commit();
        return lista;
    }
    /*public List<Carrera> getCarrerasConInscriptos() {
        em.getTransaction().begin();
        List<Carrera> carreras=new ArrayList<Carrera>();
        String jpql="SELECT c.id_carrera,c.nombre,c.duracion, COUNT(c.id_carrera) AS cant_inscriptos " +
                "FROM Carrera AS c " +
                "JOIN Carrera_Estudiante AS ce " +
                "WHERE (c.id_carrera=ce.id_carrera) " +
                "GROUP BY (c.id_carrera) " +
                "ORDER BY cant_inscriptos DESC";
        Query query= em.createQuery(jpql);
        Iterator<Carrera> it=query.getResultList().iterator();
        while(it.hasNext()){
            Carrera nuevo=it.next();
            Long id=nuevo.getId_carrera();
            String nombre=nuevo.getNombre();
            int duracion=nuevo.getDuracion();
            System.out.println("id: "+id+", nombre: "+nombre);
//            Long cantInscriptos=nuevo.getCant_inscriptos();
//            System.out.println("id: "+id+", nombre: "+nombre+", inscriptos: "+cantInscriptos);
//            carreras.add(new CarreraDto(id,nombre,duracion,cantInscriptos));
        }
        em.getTransaction().commit();

        return carreras;
    }*/
    //             Object[] result = (Object[]) it.next();
//            Long id = (Long) result[0];
//            String nombre = (String) result[1];
//            int duracion = (int) result[2];
//            Long inscriptos=(Long) result[3];
//                System.out.println("ID: " + id + ", Nombre: " + nombre + ", duracion: " + duracion+", inscriptos: "+inscriptos);
 /*   String jpql="SELECT c.id_carrera,c.nombre,c.duracion, COUNT(c.id_carrera) AS cant_inscriptos " +
            "FROM Carrera AS c " +
            "JOIN Carrera_Estudiante AS ce " +
            "WHERE (c.id_carrera=ce.id_carrera) " +
            "GROUP BY (c.id_carrera) " +
            "ORDER BY cant_inscriptos DESC";*/

    /*
     List<Object[]> resultado=query.getResultList();
    for(Object[]r: resultado) {
            String nombre = (String) r[0];
            LocalDate fecha = (LocalDate) r[1];
            Long inscriptos = (Long) r[2];
            Long graduados = (Long) r[3];
            System.out.println("nombre: "+nombre+", fecha: "+fecha.getYear()+", inscriptos: "+inscriptos+", graduados: "+graduados);
        }*/
}
