package com.crudbasics.crudbasic.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "detalle_matricula")
@Entity
public class DetalleMatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "iddetalle_matricula")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "idcurso", nullable = false, foreignKey = @ForeignKey(name = "FK_curso"))
    private CursoModel curso;

    @ManyToOne
    @JoinColumn(name = "idmatricula", nullable = false, foreignKey = @ForeignKey(name = "FK_matricula"))
    private MatriculaModel matricula;

    @Column(name = "aula", nullable = false, length = 10)
    private String aula;
}
