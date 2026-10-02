package com.upiiz.hm.infraestructure.adapters.out.persistence;

import com.upiiz.hm.domain.model.Heroe;
import com.upiiz.hm.domain.ports.out.HeroeRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HeroePersistenceAdapter implements HeroeRepositoryPort {

    private final HeroeJpaRepository jpaRepository;

    public HeroePersistenceAdapter(HeroeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    private Heroe toDomain(HeroeEntity entity) {
        return new Heroe(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getFechaNacimiento(),
                entity.getEstadoNacimiento(),
                entity.getEpoca(),
                entity.getMovimiento(),
                entity.getDescripcion()
        );
    }

    private HeroeEntity toEntity(Heroe domain) {
        return new HeroeEntity(
                domain.getId(),
                domain.getNombre(),
                domain.getApellido(),
                domain.getFechaNacimiento(),
                domain.getEstadoNacimiento(),
                domain.getEpoca(),
                domain.getMovimiento(),
                domain.getDescripcion()
        );
    }

    @Override
    public Heroe save(Heroe heroe) {
        return toDomain(jpaRepository.save(toEntity(heroe)));
    }

    @Override
    public List<Heroe> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Heroe> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByNombreAndApellido(String nombre, String apellido) {
        return jpaRepository.existsByNombreIgnoreCaseAndApellidoIgnoreCase(nombre, apellido);
    }

    @Override
    public List<Heroe> findByEpoca(String epoca) {
        return jpaRepository.findByEpocaContainingIgnoreCase(epoca).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Heroe> findByMovimiento(String movimiento) {
        return jpaRepository.findByMovimientoContainingIgnoreCase(movimiento).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Heroe> findByEstadoNacimiento(String estado) {
        return jpaRepository.findByEstadoNacimientoContainingIgnoreCase(estado).stream().map(this::toDomain).toList();
    }
}