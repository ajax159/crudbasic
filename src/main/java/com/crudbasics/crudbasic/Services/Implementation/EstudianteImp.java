package com.crudbasics.crudbasic.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crudbasics.crudbasic.Models.EstudianteModel;
import com.crudbasics.crudbasic.Repositories.EstudianteRepository;
import com.crudbasics.crudbasic.Repositories.InitialRepository;
import com.crudbasics.crudbasic.Services.EstudianteService;
import org.springframework.data.domain.Sort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteImp extends InitialCrudImpl<EstudianteModel, Integer> implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Override
    protected InitialRepository<EstudianteModel, Integer> getRepository() {
        return estudianteRepository;
    }

    @Override
    public List<EstudianteModel> findAllOrder() {
        return estudianteRepository.findAll(Sort.by(Sort.Direction.DESC, "edad"));
    }
}
