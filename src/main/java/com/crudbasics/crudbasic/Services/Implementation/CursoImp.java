package com.crudbasics.crudbasic.Services.Implementation;

import org.springframework.stereotype.Service;

import com.crudbasics.crudbasic.Models.CursoModel;
import com.crudbasics.crudbasic.Repositories.CursoRepository;
import com.crudbasics.crudbasic.Repositories.InitialRepository;
import com.crudbasics.crudbasic.Services.CursoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoImp extends InitialCrudImpl<CursoModel, Integer> implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    protected InitialRepository<CursoModel, Integer> getRepository() {
        return cursoRepository;
    }
}
