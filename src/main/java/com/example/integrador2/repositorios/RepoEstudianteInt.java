package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Estudiante;

public interface RepoEstudianteInt {

    Estudiante getEstudiantePorNumLibreta(Estudiante estu);
    void darDeAltaEstudiante(Estudiante estu);
    void deleteEstudiante(Estudiante estu);

}
