package com.crudbasics.crudbasic.Models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "matricula")
@Entity
public class MatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idmatricula")
    private Integer idMatricula;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "idestudiante_estudiante", nullable = false, foreignKey = @ForeignKey(name = "FK_ESTUDIANTE"))
    private EstudianteModel estudiante;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<DetalleMatriculaModel> detalleMatricula;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
