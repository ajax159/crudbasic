package com.crudbasics.crudbasic.Services.Implementation;

import java.util.*;

import org.springframework.stereotype.Service;

import com.crudbasics.crudbasic.Dto.CursoEstudianteDto;
import com.crudbasics.crudbasic.Models.MatriculaModel;
import com.crudbasics.crudbasic.Repositories.InitialRepository;
import com.crudbasics.crudbasic.Repositories.MatriculaRepository;
import com.crudbasics.crudbasic.Services.MatriculaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatriculaImp extends InitialCrudImpl<MatriculaModel, Integer> implements MatriculaService {

    private final MatriculaRepository matriculaRepository;

    @Override
    protected InitialRepository<MatriculaModel, Integer> getRepository() {
        return matriculaRepository;
    }

    // Mostrar la relación de cursos matriculados y sus estudiantes correspondientes
    // usando programación funcional (sugerencia, usar un Map<K,V>)
    // o Ejemplo:
    // ▪ Programación
    // • Jaime Medina
    // ▪ Base de Datos
    // • Mito X
    // • Code Y

    @Override
    public List<CursoEstudianteDto> readAllMatriculados() {
        Map<String, List<String>> matriculados = new HashMap<>();
        List<MatriculaModel> matriculas = matriculaRepository.findAll();
        matriculas.forEach(matricula -> {
            String curso = matricula.getDetalleMatricula().get(0).getCurso().getNombre();
            String estudiante = matricula.getEstudiante().getNombres() + " " + matricula.getEstudiante().getApellidos();
            if (matriculados.containsKey(curso)) {
                matriculados.get(curso).add(estudiante);
            } else {
                matriculados.put(curso, new ArrayList<>(Arrays.asList(estudiante)));
            }
        });
        List<CursoEstudianteDto> result = new ArrayList<>();
        matriculados.forEach((curso, estudiantes) -> {
            CursoEstudianteDto ce = new CursoEstudianteDto();
            ce.setCurso(curso);
            ce.setEstudiantes(estudiantes);
            result.add(ce);
        });
        return result;
    }
}
