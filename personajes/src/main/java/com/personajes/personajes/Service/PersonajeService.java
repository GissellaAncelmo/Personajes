package com.personajes.personajes.Service;

import com.personajes.personajes.Repository.PersonajeRepository;
import com.personajes.personajes.Repository.PersonajeRepository;
import com.personajes.personajes.dto.*;
import com.personajes.personajes.exception.PersonajeBadRequestException;
import com.personajes.personajes.exception.PersonajeNotFoundException;
import com.personajes.personajes.exception.PersonajeServiceException;
import com.personajes.personajes.Model.Personajes;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonajeService {

    private final PersonajeRepository personajeRepository;

    public PersonajeService(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    // CREAR PERSONAJE
    @CacheEvict(value = {"personajes", "personajes-list"}, allEntries = true)
    public PersonajeResponseDTO crearPersonaje(PersonajeCreateDTO createDTO) {
        if (personajeRepository.existsByIdentificacion(createDTO.getIdentificacion())) {
            throw new PersonajeBadRequestException("Ya existe un personaje con esa identificación");
        }

        Personajes personajes = new Personajes(
                createDTO.getNombre(),
                createDTO.getIdentificacion(),
                createDTO.getImagen(),
                createDTO.getRol(),
                createDTO.getDescripcion()
        );

        try {
            Personajes personajeGuardado = personajeRepository.save(personajes);
            return new PersonajeResponseDTO(personajeGuardado);
        } catch (Exception e) {
            throw new PersonajeServiceException("Error al crear el personaje", e);
        }
    }

    // OBTENER TODOS LOS PERSONAJES
    @Cacheable(value = "personajes-list", key = "#ordenarPor")
    @Transactional(readOnly = true)
    public List<PersonajeListDTO> obtenerTodosPersonajes(String ordenarPor) {
        List<Personajes> personajes;

        switch (ordenarPor.toLowerCase()) {
            case "nombre":
                personajes = personajeRepository.findAllOrderByNombreAsc();
                break;
            case "fecha":
                personajes = personajeRepository.findAllOrderByFechaCreacionDesc();
                break;
            default:
                personajes = personajeRepository.findAll();
        }

        return personajes.stream()
                .map(PersonajeListDTO::new)
                .collect(Collectors.toList());
    }

    // OBTENER PERSONAJE POR ID
    @Cacheable(value = "personajes", key = "#id")
    @Transactional(readOnly = true)
    public PersonajeResponseDTO obtenerPersonajePorId(Long id) {
        Personajes personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new PersonajeNotFoundException(id));
        return new PersonajeResponseDTO(personaje);
    }

    // ACTUALIZAR PERSONAJE
    @CachePut(value = "personajes", key = "#id")
    @CacheEvict(value = "personajes-list", allEntries = true)
    public PersonajeResponseDTO actualizarPersonaje(Long id, PersonajeUpdateDTO updateDTO) {
        Personajes personajeExistente = personajeRepository.findById(id)
                .orElseThrow(() -> new PersonajeNotFoundException(id));

        if (updateDTO.getNombre() != null && !updateDTO.getNombre().trim().isEmpty()) {
            personajeExistente.setNombre(updateDTO.getNombre().trim());
        }

        if (updateDTO.getImagen() != null) {
            personajeExistente.setImagen(updateDTO.getImagen().trim());
        }

        if (updateDTO.getRol() != null) {
            personajeExistente.setRol(updateDTO.getRol().trim());
        }

        if (updateDTO.getDescripcion() != null) {
            personajeExistente.setDescripcion(updateDTO.getDescripcion().trim());
        }

        try {
            Personajes personajeActualizado = personajeRepository.save(personajeExistente);
            return new PersonajeResponseDTO(personajeActualizado);
        } catch (Exception e) {
            throw new PersonajeServiceException("Error al actualizar el personaje", e);
        }
    }

    // ELIMINAR PERSONAJE
    @CacheEvict(value = {"personajes", "personajes-list"}, allEntries = true)
    public void eliminarPersonaje(Long id) {
        if (!personajeRepository.existsById(id)) {
            throw new PersonajeNotFoundException(id);
        }

        try {
            personajeRepository.deleteById(id);
        } catch (Exception e) {
            throw new PersonajeServiceException("Error al eliminar el personaje", e);
        }
    }

    // BUSCAR PERSONAJES
    @Transactional(readOnly = true)
    public List<PersonajeListDTO> buscarPersonajes(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new PersonajeBadRequestException("El texto de búsqueda no puede estar vacío");
        }

        List<Personajes> personajes = personajeRepository.busquedaGlobal(texto.trim());

        return personajes.stream()
                .map(PersonajeListDTO::new)
                .collect(Collectors.toList());
    }

    // OBTENER POR IDENTIFICACIÓN
    @Transactional(readOnly = true)
    public PersonajeResponseDTO obtenerPersonajePorIdentificacion(String identificacion) {
        Personajes personajes = personajeRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new PersonajeNotFoundException("identificación", identificacion));
        return new PersonajeResponseDTO(personajes);
    }

    // VERIFICAR EXISTENCIA
    @Transactional(readOnly = true)
    public boolean existePersonaje(Long id) {
        return personajeRepository.existsById(id);
    }

    // CONTAR PERSONAJES
    @Cacheable(value = "personajes-count")
    @Transactional(readOnly = true)
    public long contarPersonajes() {
        return personajeRepository.count();
    }

    // OBTENER RECIENTES
    @Transactional(readOnly = true)
    public List<PersonajeListDTO> obtenerPersonajesRecientes(int limite) {
        if (limite <= 0 || limite > 100) {
            throw new PersonajeBadRequestException("El límite debe estar entre 1 y 100");
        }

        List<Personajes> personajes = personajeRepository.findTopNByOrderByFechaCreacionDesc(limite);

        return personajes.stream()
                .map(PersonajeListDTO::new)
                .collect(Collectors.toList());
    }

    // LIMPIAR CACHE
    @CacheEvict(value = {"personajes", "personajes-list", "personajes-count"}, allEntries = true)
    public void limpiarCache() {

    }
}
