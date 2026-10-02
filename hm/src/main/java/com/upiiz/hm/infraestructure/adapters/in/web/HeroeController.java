package com.upiiz.hm.infraestructure.adapters.in.web;

import com.upiiz.hm.domain.model.Heroe;
import com.upiiz.hm.domain.ports.in.HeroeServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroeController {

    private final HeroeServicePort heroeService;

    public HeroeController(HeroeServicePort heroeService) {
        this.heroeService = heroeService;
    }

    @PostMapping
    public ResponseEntity<Heroe> registrar(@RequestBody Heroe heroe) {
        return new ResponseEntity<>(heroeService.registrar(heroe), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Heroe>> listarTodos() {
        return ResponseEntity.ok(heroeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Heroe> buscarPorId(@PathVariable Long id) {
        return heroeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroe> modificar(@PathVariable Long id, @RequestBody Heroe heroe) {
        return ResponseEntity.ok(heroeService.modificar(id, heroe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        heroeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/epoca/{epoca}")
    public ResponseEntity<List<Heroe>> buscarPorEpoca(@PathVariable String epoca) {
        return ResponseEntity.ok(heroeService.buscarPorEpoca(epoca));
    }

    @GetMapping("/movimiento/{movimiento}")
    public ResponseEntity<List<Heroe>> buscarPorMovimiento(@PathVariable String movimiento) {
        return ResponseEntity.ok(heroeService.buscarPorMovimiento(movimiento));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Heroe>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(heroeService.buscarPorEstado(estado));
    }
}