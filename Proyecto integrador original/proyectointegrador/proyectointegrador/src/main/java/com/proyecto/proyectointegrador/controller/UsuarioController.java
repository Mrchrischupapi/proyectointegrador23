package com.proyecto.proyectointegrador.controller;

import com.proyecto.proyectointegrador.model.Proyecto;
import com.proyecto.proyectointegrador.model.RespuestaEvaluacion;
import com.proyecto.proyectointegrador.model.Usuario;
import com.proyecto.proyectointegrador.repository.ProyectoRepository;
import com.proyecto.proyectointegrador.repository.RespuestaEvaluacionRepository;
import com.proyecto.proyectointegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaEvaluacionRepository respuestaEvaluacionRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    // --- AUTENTICACIÓN Y VISTAS ---

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    @ResponseBody
    public ResponseEntity<String> guardarUsuario(@ModelAttribute Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al guardar en la base de datos");
        }
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam("correo") String correo,
                                @RequestParam("password") String password) {

        Optional<Usuario> usuarioValido = usuarioRepository.findByCorreoAndPassword(correo, password);

        if (usuarioValido.isPresent()) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "dashboard";
    }

    // --- ENDPOINTS REST PARA PROYECTOS ---

    @GetMapping("/api/proyectos")
    @ResponseBody
    public List<Proyecto> listarProyectos() {
        return proyectoRepository.findAll();
    }

    @PostMapping("/api/proyectos")
    @ResponseBody
    public ResponseEntity<String> guardarProyecto(@RequestBody Proyecto proyecto) {
        try {
            if (proyecto.getEstado() == null) {
                proyecto.setEstado("ACTIVO");
            }
            proyectoRepository.save(proyecto);
            return ResponseEntity.ok("Proyecto guardado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al guardar el proyecto");
        }
    }

    @PutMapping("/api/proyectos/{id}")
    @ResponseBody
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto datos) {
        return proyectoRepository.findById(id).map(p -> {
            p.setNombre(datos.getNombre());
            p.setInstrucciones(datos.getInstrucciones());
            if (datos.getDescripcion() != null) p.setDescripcion(datos.getDescripcion());
            if (datos.getUrl() != null) p.setUrl(datos.getUrl());
            return ResponseEntity.ok(proyectoRepository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/proyectos/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> eliminarProyecto(@PathVariable("id") Long id) {
        try {
            List<RespuestaEvaluacion> evaluaciones = respuestaEvaluacionRepository.findByProyectoId(id);
            if (!evaluaciones.isEmpty()) {
                respuestaEvaluacionRepository.deleteAll(evaluaciones);
            }
            proyectoRepository.deleteById(id);
            return ResponseEntity.ok("Proyecto eliminado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al eliminar el proyecto");
        }
    }

    // --- ENDPOINTS PARA EVALUACIONES ---

    @GetMapping("/api/proyectos/evaluacion")
    public ModelAndView mostrarEvaluacionTemplate() {
        return new ModelAndView("proyecto_evaluacion");
    }

    @GetMapping("/proyecto_evaluacion")
    public String mostrarEvaluacion() {
        return "proyecto_evaluacion";
    }

    @PostMapping("/api/proyectos/evaluacion/guardar")
    @ResponseBody
    public ResponseEntity<String> guardarEvaluacion(@RequestBody RespuestaEvaluacion respuesta) {
        try {
            respuestaEvaluacionRepository.save(respuesta);
            return ResponseEntity.ok("Evaluación guardada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al guardar en la base de datos");
        }
    }

    @GetMapping("/evaluaciones/tabla")
    public String mostrarTablaEvaluaciones() {
        return "evaluaciones_tabla";
    }

    @GetMapping("/api/proyectos/evaluacion/listar")
    @ResponseBody
    public List<RespuestaEvaluacion> listarEvaluaciones() {
        return respuestaEvaluacionRepository.findAll();
    }

    @GetMapping("/api/proyectos/evaluacion/proyecto/{proyectoId}")
    @ResponseBody
    public List<RespuestaEvaluacion> listarEvaluacionesPorProyecto(@PathVariable("proyectoId") Long proyectoId) {
        return respuestaEvaluacionRepository.findByProyectoId(proyectoId);
    }

    // --- ENDPOINT PARA ELIMINAR EVALUACIONES ---

    @DeleteMapping("/api/proyectos/evaluacion/eliminar/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> eliminarEvaluacion(@PathVariable("id") Long id) {
        try {
            if (respuestaEvaluacionRepository.existsById(id)) {
                respuestaEvaluacionRepository.deleteById(id);
                return ResponseEntity.ok("Evaluación eliminada correctamente");
            } else {
                return ResponseEntity.status(404).body("Evaluación no encontrada");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al eliminar la evaluación");
        }
    }
}