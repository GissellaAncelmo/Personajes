package com.personajes.personajes.dto;

import jakarta.validation.constraints.*;

public class PersonajeUpdateDTO {

    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    private String imagen;

    @Size(max = 100, message = "El rol no puede exceder 100 caracteres")
    private String rol;

    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String descripcion;

    public PersonajeUpdateDTO() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
