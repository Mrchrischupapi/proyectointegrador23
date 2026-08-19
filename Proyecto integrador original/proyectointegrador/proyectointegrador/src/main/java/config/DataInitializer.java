package com.proyecto.proyectointegrador.config;

import com.proyecto.proyectointegrador.model.Usuario;
import com.proyecto.proyectointegrador.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            String correoAdmin = "admin@gmail.com";

            // Revisa si ya existe para evitar registros duplicados
            if (usuarioRepository.findByCorreo(correoAdmin).isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setCorreo(correoAdmin);
                admin.setPassword("admin");

                usuarioRepository.save(admin);
                System.out.println("==========================================");
                System.out.println(" USUARIO ADMIN CREADO EN ORACLE CLOUD:");
                System.out.println("   Usuario / Correo: admin");
                System.out.println("   Contraseña: admin");
                System.out.println("==========================================");
            }
        };
    }
}