package com.personajes.personajes.dto;

import jakarta.validation.constraints.*;

public class PersonajeCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La identificación es obligatoria")
    @Size(min = 3, max = 50, message = "La identificación debe tener entre 3 y 50 caracteres")
    private String identificacion;

    private String imagen;

    @Size(max = 100, message = "El rol no puede exceder 100 caracteres")
    private String rol;

    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String descripcion;

    public PersonajeCreateDTO() {}

    public PersonajeCreateDTO(String nombre, String identificacion, String imagen, String rol, String descripcion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.imagen = imagen;
        this.rol = rol;
        this.descripcion = descripcion;
    }

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
}