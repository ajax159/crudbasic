package com.crudbasics.crudbasic.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crudbasics.crudbasic.Dto.CursoDto;
import com.crudbasics.crudbasic.Dto.GenericResponseRecord;
import com.crudbasics.crudbasic.Models.CursoModel;
import com.crudbasics.crudbasic.Services.CursoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponseRecord<CursoDto>> readAll() throws Exception {
        List<CursoDto> list = cursoService.readAll().stream().map(this::converToDto).toList();
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseRecord<CursoDto>> readById(@PathVariable Integer id) throws Exception {
        CursoDto cursoDto = converToDto(cursoService.readById(id));
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", Arrays.asList(cursoDto)));
    }

    @PostMapping
    public ResponseEntity<CursoDto> save(@Valid @RequestBody CursoDto cursoDto) throws Exception {
        CursoModel object = cursoService.save(convertToEntity(cursoDto));
        return new ResponseEntity<>(converToDto(object), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDto> update(@Valid @PathVariable("id") Integer id, @RequestBody CursoDto cursoDto)
            throws Exception {
        CursoModel object = cursoService.update(convertToEntity(cursoDto), id);
        return ResponseEntity.ok(converToDto(object));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CursoDto converToDto(CursoModel object) {
        return modelMapper.map(object, CursoDto.class);
    }

    private CursoModel convertToEntity(CursoDto object) {
        return modelMapper.map(object, CursoModel.class);
    }
}
