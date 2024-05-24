package com.crudbasics.crudbasic.Services;

import java.util.List;

import com.crudbasics.crudbasic.Dto.CursoEstudianteDto;
import com.crudbasics.crudbasic.Models.MatriculaModel;

public interface MatriculaService extends InitialCrud<MatriculaModel, Integer> {
    List<CursoEstudianteDto> readAllMatriculados();
}
