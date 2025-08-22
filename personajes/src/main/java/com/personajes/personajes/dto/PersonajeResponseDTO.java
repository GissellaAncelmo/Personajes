package com.personajes.personajes.dto;

import com.personajes.personajes.Model.Personajes;
import java.time.LocalDateTime;

public class PersonajeResponseDTO {

    private Long id;
    private String nombre;
    private String identificacion;
    private String imagen;
    private String rol;
    private String descripcion;
    private LocalDateTime fechaCreacion;

    public PersonajeResponseDTO() {}

    public PersonajeResponseDTO(Personajes personajes) {
        this.id = personajes.getId();
        this.nombre = personajes.getNombre();
        this.identificacion = personajes.getIdentificacion();
        this.imagen = personajes.getImagen();
        this.rol = personajes.getRol();
        this.descripcion = personajes.getDescripcion();
        this.fechaCreacion = personajes.getFechaCreacion();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}