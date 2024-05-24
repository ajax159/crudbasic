package com.crudbasics.crudbasic.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crudbasics.crudbasic.Dto.EstudianteDto;
import com.crudbasics.crudbasic.Dto.GenericResponseRecord;
import com.crudbasics.crudbasic.Models.EstudianteModel;
import com.crudbasics.crudbasic.Services.EstudianteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponseRecord<EstudianteDto>> readAll() throws Exception {
        List<EstudianteDto> list = estudianteService.readAll().stream().map(this::converToDto).toList();
        System.out.println(list);
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseRecord<EstudianteDto>> readById(@PathVariable Integer id) throws Exception {
        EstudianteDto estudianteDto = converToDto(estudianteService.readById(id));
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", Arrays.asList(estudianteDto)));
    }

    @PostMapping
    public ResponseEntity<EstudianteDto> save(@Valid @RequestBody EstudianteDto estudianteDto) throws Exception {
        EstudianteModel object = estudianteService.save(convertToEntity(estudianteDto));
        return new ResponseEntity<>(converToDto(object), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDto> update(@Valid @PathVariable("id") Integer id,
            @RequestBody EstudianteDto estudianteDto)
            throws Exception {
        EstudianteModel object = estudianteService.update(convertToEntity(estudianteDto), id);
        return ResponseEntity.ok(converToDto(object));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Listar estudiantes ordenados de forma descendente por edad usando
    // programación funcional
    @GetMapping("/order")
    public ResponseEntity<List<EstudianteDto>> findAllOrder(
            @RequestBody EstudianteDto estudianteDto) {
        List<EstudianteDto> list = estudianteService.findAllOrder().stream().map(this::converToDto).toList();

        return ResponseEntity.ok(list);
    }

    private EstudianteDto converToDto(EstudianteModel obj) {
        return modelMapper.map(obj, EstudianteDto.class);
    }

    private EstudianteModel convertToEntity(EstudianteDto dto) {
        return modelMapper.map(dto, EstudianteModel.class);
    }
}
