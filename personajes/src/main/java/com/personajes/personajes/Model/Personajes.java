package com.personajes.personajes.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "personajes")
public class Personajes {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message =  "El nombre es obligatorio")
    private String nombre;

    @Column(name = "identificacion", unique = true, nullable = false)  // Columna única y obligatoria
    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "rol")
    private String rol;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Constructor vacío
    public Personajes() {}

    public Personajes(Long id, String nombre, String identificacion, String imagen, String rol, String descripcion, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.imagen = imagen;
        this.rol = rol;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public Personajes(String nombre, String identificacion, String imagen, String rol, String descripcion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.imagen = imagen;
        this.rol = rol;
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "La identificación es obligatoria") String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(@NotBlank(message = "La identificación es obligatoria") String identificacion) {
        this.identificacion = identificacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "personajes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", rol='" + rol + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personajes that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(identificacion, that.identificacion) && Objects.equals(imagen, that.imagen) && Objects.equals(rol, that.rol) && Objects.equals(descripcion, that.descripcion) && Objects.equals(fechaCreacion, that.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, identificacion, imagen, rol, descripcion, fechaCreacion);
    }
}
