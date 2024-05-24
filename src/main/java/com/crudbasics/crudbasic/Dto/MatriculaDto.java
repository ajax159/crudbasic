package com.crudbasics.crudbasic.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDto {

    private Integer idMatricula;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    private EstudianteDto estudiante;

    @NotNull
    private Boolean estado;

    @NotNull
    @JsonManagedReference
    private List<DetalleMatriculaDto> detalleMatricula;
}
