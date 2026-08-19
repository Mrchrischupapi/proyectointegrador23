package com.proyecto.proyectointegrador.repository;

import com.proyecto.proyectointegrador.model.RespuestaEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaEvaluacionRepository extends JpaRepository<RespuestaEvaluacion, Long> {

    // Consulta SQL Nativa garantizada para Oracle
    @Query(value = "SELECT * FROM RESPUESTAS_EVALUACION WHERE PROYECTO_ID = :proyectoId", nativeQuery = true)
    List<RespuestaEvaluacion> findByProyectoId(@Param("proyectoId") Long proyectoId);

}