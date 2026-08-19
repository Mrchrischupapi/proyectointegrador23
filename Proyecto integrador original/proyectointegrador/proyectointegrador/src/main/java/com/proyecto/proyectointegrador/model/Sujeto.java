package com.proyecto.proyectointegrador.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sujetos")
@Data
public class Sujeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sujeto_seq")
    @SequenceGenerator(name = "sujeto_seq", sequenceName = "SUJETOS_SEQ", allocationSize = 1)
    private Long id;

    private String nombre;
}