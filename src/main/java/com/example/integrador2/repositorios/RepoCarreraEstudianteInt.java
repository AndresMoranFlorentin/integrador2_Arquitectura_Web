package com.example.integrador2.repositorios;

import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

public interface RepoCarreraEstudianteInt {
    void matricularEstudiante(Estudiante estu, Carrera carrera);
}
