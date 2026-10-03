package com.upiiz.hm.application.service;

import com.upiiz.hm.domain.model.Heroe;
import com.upiiz.hm.domain.ports.in.HeroeServicePort;
import com.upiiz.hm.domain.ports.out.HeroeRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HeroeService implements HeroeServicePort {

    private final HeroeRepositoryPort heroeRepository;

    public HeroeService(HeroeRepositoryPort heroeRepository) {
        this.heroeRepository = heroeRepository;
    }

    private void validarHeroe(Heroe heroe) {
        if (heroe.getNombre() == null || heroe.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (heroe.getApellido() == null || heroe.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (heroe.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (heroe.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }
        if (heroe.getEstadoNacimiento() == null || heroe.getEstadoNacimiento().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de nacimiento es obligatorio.");
        }
        if (heroe.getEpoca() == null || heroe.getEpoca().trim().isEmpty()) {
            throw new IllegalArgumentException("La época histórica es obligatoria.");
        }
        if (heroe.getMovimiento() == null || heroe.getMovimiento().trim().isEmpty()) {
            throw new IllegalArgumentException("El movimiento es obligatorio.");
        }
    }

    @Override
    public Heroe registrar(Heroe heroe) {
        validarHeroe(heroe);
        if (heroeRepository.existsByNombreAndApellido(heroe.getNombre().trim(), heroe.getApellido().trim())) {
            throw new IllegalArgumentException("Ya existe un héroe registrado con el mismo nombre y apellido.");
        }
        return heroeRepository.save(heroe);
    }

    @Override
    public List<Heroe> listarTodos() {
        return heroeRepository.findAll();
    }

    @Override
    public Optional<Heroe> buscarPorId(Long id) {
        return heroeRepository.findById(id);
    }

    @Override
    public Heroe modificar(Long id, Heroe heroe) {
        if (heroeRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("No se puede modificar: el héroe con ID " + id + " no existe.");
        }
        validarHeroe(heroe);
        heroe.setId(id);
        return heroeRepository.save(heroe);
    }

    @Override
    public void eliminar(Long id) {
        if (heroeRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("No se puede eliminar: el héroe con ID " + id + " no existe.");
        }
        heroeRepository.deleteById(id);
    }

    @Override
    public List<Heroe> buscarPorEpoca(String epoca) {
        return heroeRepository.findByEpoca(epoca);
    }

    @Override
    public List<Heroe> buscarPorMovimiento(String movimiento) {
        return heroeRepository.findByMovimiento(movimiento);
    }

    @Override
    public List<Heroe> buscarPorEstado(String estado) {
        return heroeRepository.findByEstadoNacimiento(estado);
    }
}