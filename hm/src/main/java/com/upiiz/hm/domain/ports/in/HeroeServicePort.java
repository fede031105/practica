package com.upiiz.hm.domain.ports.in;

import com.upiiz.hm.domain.model.Heroe;
import java.util.List;
import java.util.Optional;

public interface HeroeServicePort {
    Heroe registrar(Heroe heroe);
    List<Heroe> listarTodos();
    Optional<Heroe> buscarPorId(Long id);
    Heroe modificar(Long id, Heroe heroe);
    void eliminar(Long id);
    List<Heroe> buscarPorEpoca(String epoca);
    List<Heroe> buscarPorMovimiento(String movimiento);
    List<Heroe> buscarPorEstado(String estado);
}