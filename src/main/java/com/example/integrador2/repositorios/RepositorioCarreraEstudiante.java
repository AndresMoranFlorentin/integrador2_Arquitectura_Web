package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Carrera_Estudiante;
import com.example.integrador2.entidades.Estudiante;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalDate.now;

/**
 * Clase encargada de ejecutar las operaciones de baja alta modificacion
 * de la Entidad Carrera_Estudiante y que estos cambios se persistan
 * @version 25/4/2024 A.0
 */
public class RepositorioCarreraEstudiante implements RepoCarreraEstudianteInt{
    private EntityManager em=null;
    /**
     * Constructor vacio, se crea la clase con todos sus atributos nulos
     */
    public RepositorioCarreraEstudiante(){

    }
    /**
     * Constructor que recibe al entity manager que le permitira interactuar con la base de datos
     * @param em objeto Entity Manager
     */
    public RepositorioCarreraEstudiante(EntityManager em){
        this.em=em;
    }
    /**
     * Metodo responsable de matricular a un estudiante a una carrera y que persista en la base de datos
     * @param estu Estudiante que se matriculara
     * @param c Carrera en la que el estudiante se inscribira
     * @param id un identificador para el nuevo registro
     */
    @Override
    public void matricularEstudiante(Estudiante estu, Carrera c, Long id) {
        em.getTransaction().begin();
       //variables que cargaran los datos para el nuevo registro de estudiante_carrera
        int hoy=LocalDate.now().getYear();
        //esto es algo grave porque el id deberia irse auto-incrementando
        Long dni=estu.getDni();
        Long idCarre=c.getId_carrera();
        int fechaInscripcion= LocalDate.now().getYear();

        int antiguedad=0;
        //se crea el nuevo registro
        Carrera_Estudiante nuevoRegistro=new Carrera_Estudiante(id,dni,idCarre,fechaInscripcion,0,antiguedad);
        em.persist(nuevoRegistro);

        //se agrega el registro al estudiante y la carrera
        estu.addCarrera(nuevoRegistro);
        em.merge(estu);

        c.addEstudianteAlaCarrera(nuevoRegistro);
        em.merge(c);

        //ahora se suben los cambios y se persiste este nuevo registro

        em.getTransaction().commit();
    }
}
