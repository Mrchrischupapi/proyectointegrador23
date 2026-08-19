package com.proyecto.proyectointegrador.repository;

import com.proyecto.proyectointegrador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su correo y contraseña (para el Login)
    Optional<Usuario> findByCorreoAndPassword(String correo, String password);

    // Busca un usuario solo por correo (para verificar si ya existe)
    Optional<Usuario> findByCorreo(String correo);
}