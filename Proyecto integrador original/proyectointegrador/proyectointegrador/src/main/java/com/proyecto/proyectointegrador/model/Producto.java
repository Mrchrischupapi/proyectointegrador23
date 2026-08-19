package com.proyecto.proyectointegrador.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @SequenceGenerator(name = "producto_seq", sequenceName = "PRODUCTOS_SEQ", allocationSize = 1)
    private Long id;

    private String nombre;
    private Double precio;
}