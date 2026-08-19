package com.proyecto.proyectointegrador.repository;

import com.proyecto.proyectointegrador.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}