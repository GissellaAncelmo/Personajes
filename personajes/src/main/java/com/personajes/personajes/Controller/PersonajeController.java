package com.personajes.personajes.Controller;

import com.personajes.personajes.dto.PersonajeListDTO;
import com.personajes.personajes.dto.PersonajeListDTO;
import com.personajes.personajes.dto.*;
import com.personajes.personajes.Service.PersonajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  //  maneja endpoints REST
@RequestMapping("/api/personajes")  // Todas las rutas empiezan con /api/personajes
public class PersonajeController {

    private final PersonajeService personajeService;

    // Inyección de dependencias por constructor
    @Autowired
    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    // GET /api/personajes - OBTENER TODOS LOS PERSONAJES

    @GetMapping
    public ResponseEntity<List<PersonajeListDTO>> obtenerTodosPersonajes(
            @RequestParam(value = "sort", defaultValue = "id") String ordenarPor) {

        List<PersonajeListDTO> personajes = personajeService.obtenerTodosPersonajes(ordenarPor);
        return ResponseEntity.ok(personajes);  // 200 OK + lista de personajes
    }

    // GET /api/personajes/{id} - OBTENER PERSONAJE POR ID

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeResponseDTO> obtenerPersonajePorId(@PathVariable Long id) {

        PersonajeResponseDTO personaje = personajeService.obtenerPersonajePorId(id);
        return ResponseEntity.ok(personaje);  // 200 OK + datos del personaje
    }

    // POST /api/personajes - CREAR NUEVO PERSONAJE

    @PostMapping
    public ResponseEntity<PersonajeResponseDTO> crearPersonaje(
            @Valid @RequestBody PersonajeCreateDTO createDTO) {

        PersonajeResponseDTO nuevoPersonaje = personajeService.crearPersonaje(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersonaje);  // 201 Created + personaje creado
    }

    // PUT /api/personajes/{id} - ACTUALIZAR PERSONAJE

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeResponseDTO> actualizarPersonaje(
            @PathVariable Long id,
            @Valid @RequestBody PersonajeUpdateDTO updateDTO) {

        PersonajeResponseDTO personajeActualizado = personajeService.actualizarPersonaje(id, updateDTO);
        return ResponseEntity.ok(personajeActualizado);  // 200 OK + personaje actualizado
    }

    // DELETE /api/personajes/{id} - ELIMINAR PERSONAJE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarPersonaje(@PathVariable Long id) {

        personajeService.eliminarPersonaje(id);

        // Respuesta de confirmación
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Personaje eliminado exitosamente");
        response.put("id", id);
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.ok(response);  // 200 OK + mensaje de confirmación
    }

    // GET /api/personajes/buscar - BUSCAR PERSONAJES

    @GetMapping("/buscar")
    public ResponseEntity<List<PersonajeListDTO>> buscarPersonajes(
            @RequestParam(value = "q") String texto) {

        List<PersonajeListDTO> personajes = personajeService.buscarPersonajes(texto);
        return ResponseEntity.ok(personajes);  // 200 OK + resultados de búsqueda
    }

    // GET /api/personajes/identificacion/{identificacion} - OBTENER POR IDENTIFICACIÓN

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<PersonajeResponseDTO> obtenerPersonajePorIdentificacion(
            @PathVariable String identificacion) {

        PersonajeResponseDTO personaje = personajeService.obtenerPersonajePorIdentificacion(identificacion);
        return ResponseEntity.ok(personaje);  // 200 OK + datos del personaje
    }

    // GET /api/personajes/recientes - OBTENER PERSONAJES RECIENTES

    @GetMapping("/recientes")
    public ResponseEntity<List<PersonajeListDTO>> obtenerPersonajesRecientes(
            @RequestParam(value = "limite", defaultValue = "10") int limite) {

        List<PersonajeListDTO> personajes = personajeService.obtenerPersonajesRecientes(limite);
        return ResponseEntity.ok(personajes);  // 200 OK + personajes recientes
    }

    // GET /api/personajes/estadisticas - OBTENER ESTADÍSTICAS

    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {

        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("totalPersonajes", personajeService.contarPersonajes());
        estadisticas.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.ok(estadisticas);  // 200 OK + estadísticas
    }

    // POST /api/personajes/limpiar-cache - LIMPIAR CACHE

    @PostMapping("/limpiar-cache")
    public ResponseEntity<Map<String, Object>> limpiarCache() {

        personajeService.limpiarCache();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cache limpiado exitosamente");
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.ok(response);  // 200 OK + confirmación
    }

    // GET /api/personajes/verificar/{id} - VERIFICAR SI EXISTE

    @GetMapping("/verificar/{id}")
    public ResponseEntity<Map<String, Object>> verificarExistencia(@PathVariable Long id) {

        boolean existe = personajeService.existePersonaje(id);

        Map<String, Object> response = new HashMap<>();
        response.put("existe", existe);
        response.put("id", id);
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.ok(response);  // 200 OK + resultado de verificación
    }

    // GET /api/personajes/health - HEALTH CHECK

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {

        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("message", "API de Personajes funcionando correctamente");
        health.put("timestamp", System.currentTimeMillis());
        health.put("totalPersonajes", personajeService.contarPersonajes());

        return ResponseEntity.ok(health);  // 200 OK + estado de salud
    }
}
