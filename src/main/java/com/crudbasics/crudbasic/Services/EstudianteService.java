package com.crudbasics.crudbasic.Services;

import java.util.List;

import com.crudbasics.crudbasic.Models.EstudianteModel;

public interface EstudianteService extends InitialCrud<EstudianteModel, Integer> {
    List<EstudianteModel> findAllOrder();
}
