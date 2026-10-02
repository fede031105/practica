package com.upiiz.hm.infraestructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroeJpaRepository extends JpaRepository<HeroeEntity, Long> {
    boolean existsByNombreIgnoreCaseAndApellidoIgnoreCase(String nombre, String apellido);
    List<HeroeEntity> findByEpocaContainingIgnoreCase(String epoca);
    List<HeroeEntity> findByMovimientoContainingIgnoreCase(String movimiento);
    List<HeroeEntity> findByEstadoNacimientoContainingIgnoreCase(String estadoNacimiento);
}