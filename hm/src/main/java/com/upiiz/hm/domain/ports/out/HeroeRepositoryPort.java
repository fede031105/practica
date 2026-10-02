package com.upiiz.hm.domain.ports.out;

import com.upiiz.hm.domain.model.Heroe;
import java.util.List;
import java.util.Optional;

public interface HeroeRepositoryPort {
    Heroe save(Heroe heroe);
    List<Heroe> findAll();
    Optional<Heroe> findById(Long id);
    void deleteById(Long id);
    boolean existsByNombreAndApellido(String nombre, String apellido);
    List<Heroe> findByEpoca(String epoca);
    List<Heroe> findByMovimiento(String movimiento);
    List<Heroe> findByEstadoNacimiento(String estado);
}