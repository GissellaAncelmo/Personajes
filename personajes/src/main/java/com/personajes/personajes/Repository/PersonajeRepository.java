package com.personajes.personajes.Repository;

import com.personajes.personajes.Model.Personajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personajes, Long> {

    Optional<Personajes> findByIdentificacion(String identificacion);

    boolean existsByIdentificacion(String identificacion);

    List<Personajes> findByNombreContainingIgnoreCase(String nombre);

    List<Personajes> findByRol(String rol);

    @Query("SELECT p FROM Personajes p ORDER BY p.nombre ASC")
    List<Personajes> findAllOrderByNombreAsc();

    @Query("SELECT p FROM Personajes p ORDER BY p.fechaCreacion DESC")
    List<Personajes> findAllOrderByFechaCreacionDesc();

    @Query("SELECT p FROM Personajes p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) ORDER BY p.nombre")
    List<Personajes> findByNombreContainingOrderByNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM Personajes p ORDER BY p.fechaCreacion DESC LIMIT :limit")
    List<Personajes> findTopNByOrderByFechaCreacionDesc(@Param("limit") int limit);

    @Query("SELECT p.rol, COUNT(p) FROM Personajes p GROUP BY p.rol")
    List<Object[]> countPersonajesByRol();

    @Query("SELECT p FROM Personajes p WHERE " +
            "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
            "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
            "LOWER(p.rol) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Personajes> busquedaGlobal(@Param("texto") String texto);
}