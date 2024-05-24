package com.crudbasics.crudbasic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "curso")
@Entity
public class CursoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idcurso")
    private Integer idCurso;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "siglas", nullable = false, length = 10)
    private String siglas;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

}
