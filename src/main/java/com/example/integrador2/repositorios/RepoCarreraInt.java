package com.example.integrador2.repositorios;

import com.example.integrador2.dto.CarreraDto;
import com.example.integrador2.dto.ReporteTdo;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import java.util.List;

/**
 * Interface encargada de darle comportamiento a los repositorios de la entidad Carrera
 * @version 25/4/2024 A.0
 * @see RepositorioCarrera
 */
public interface RepoCarreraInt {
    /**
     * Metodo para borrar una carrera de la base de datos
     * @param carrera recibe la carrera que desea eliminar el usuario
     */
    void delete(Carrera carrera);

    /**
     * Metodo para pedir una carrera en especifico
     * @param carrera  es un identificador de la carrera que se busca
     * @return retorna una Carrera
     */
    Carrera getCarrera(Long carrera);

    /**
     * Metodo encargado de devolver una lista de las carreras
     * que tienen inscriptos y cuantos son
     * @return devuelve una lista de objetos CarreraDto, que tienen un atributo mas que
     * la entidad Carrera y es la cantidad de inscriptos
     * @see CarreraDto
     */
    List<CarreraDto> getCarrerasConInscriptos();

    /**
     * Metodo encargado de traer una lista de todas las carreras con la cantidad de inscriptos
     * y graduados por año
     * @return retorna una lista de ReporteTdo
     */
    List<ReporteTdo> getCarrerasConInscriptosEgresados();
}
