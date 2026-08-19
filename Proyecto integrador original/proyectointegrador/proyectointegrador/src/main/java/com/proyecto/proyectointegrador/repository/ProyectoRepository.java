package com.proyecto.proyectointegrador.repository;

import com.proyecto.proyectointegrador.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}