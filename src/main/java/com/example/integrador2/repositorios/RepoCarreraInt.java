package com.example.integrador2.repositorios;

import com.example.integrador2.dto.CarreraDto;
import com.example.integrador2.entidades.Carrera;
import com.example.integrador2.entidades.Estudiante;

import java.util.List;

public interface RepoCarreraInt {
    void delete(Carrera carrera);
    Carrera getCarrera(Long carrera);
    List<CarreraDto> getCarrerasConInscriptos();
}
