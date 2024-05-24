package com.crudbasics.crudbasic.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crudbasics.crudbasic.Dto.CursoEstudianteDto;
import com.crudbasics.crudbasic.Dto.GenericResponseRecord;
import com.crudbasics.crudbasic.Dto.MatriculaDto;
import com.crudbasics.crudbasic.Models.MatriculaModel;
import com.crudbasics.crudbasic.Services.MatriculaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponseRecord<MatriculaDto>> readAll() throws Exception {
        List<MatriculaDto> list = matriculaService.readAll().stream().map(this::converToDto).toList();
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseRecord<MatriculaDto>> readById(@PathVariable Integer id) throws Exception {
        MatriculaDto matriculaDto = converToDto(matriculaService.readById(id));
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", Arrays.asList(matriculaDto)));
    }

    @PostMapping
    public ResponseEntity<MatriculaDto> save(@Valid @RequestBody MatriculaDto matriculaDto) throws Exception {
        MatriculaModel object = matriculaService.save(convertToEntity(matriculaDto));
        return new ResponseEntity<>(converToDto(object), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDto> update(@Valid @PathVariable("id") Integer id,
            @RequestBody MatriculaDto matriculaDto)
            throws Exception {
        MatriculaModel object = matriculaService.update(convertToEntity(matriculaDto), id);
        return ResponseEntity.ok(converToDto(object));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Mostrar la relación de cursos matriculados y sus estudiantes correspondientes
    // usando programación funcional (sugerencia, usar un Map<K,V>)
    // o Ejemplo:
    // ▪ Programación
    // • Jaime Medina
    // ▪ Base de Datos
    // • Mito X
    // • Code Y
    @GetMapping("/matriculados")
    public ResponseEntity<GenericResponseRecord<CursoEstudianteDto>> readAllMatriculados() throws Exception {
        List<CursoEstudianteDto> list = matriculaService.readAllMatriculados();
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    private MatriculaDto converToDto(MatriculaModel object) {
        return modelMapper.map(object, MatriculaDto.class);
    }

    private MatriculaModel convertToEntity(MatriculaDto object) {
        return modelMapper.map(object, MatriculaModel.class);
    }

}
