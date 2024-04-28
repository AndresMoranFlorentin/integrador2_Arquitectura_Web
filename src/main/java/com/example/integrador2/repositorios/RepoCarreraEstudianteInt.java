package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

/**
 * Es una interface hecha para heredar comportamientos a la clase que los implementa
 * en este caso es en la entidad CarreraEstudiante que guarda la relacion entre el estudiante y la carrera
 * y matricular un estudiante es responsabilidad de dicha entidad
 * @version 25/4/2024 A.0
 */
public interface RepoCarreraEstudianteInt {
    /**
     * Metodo abstracto responsable de matricular a un estudiante a una carrera
     * @param estu objeto Estudiante
     * @param carrera objeto Carrera
     * @param id un id para el registo
     */
    void matricularEstudiante(Estudiante estu, Carrera carrera,Long id);
}
