package com.crudbasics.crudbasic.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDto {

    private Integer idEstudiante;

    @NotNull
    @Size(min = 3)
    private String nombres;

    @NotNull
    @Size(min = 3)
    private String apellidos;

    @NotNull
    @Size(min = 8, max = 8)
    private String dni;

    @NotNull
    private Integer edad;
}
