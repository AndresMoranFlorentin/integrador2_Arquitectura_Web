package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import java.util.List;

public interface RepoEstudianteInt {

    Estudiante getEstudiantePorNumLibreta(Long id);
    List<Estudiante> getEstudiantePorGenero(String genero);
    List<Estudiante> getEstudiantePorCarrera(Carrera c, String ciudad);
    void darDeAltaEstudiante(Estudiante estu);
    void deleteEstudiante(Estudiante estu);

}
