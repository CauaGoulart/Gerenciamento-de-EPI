package com.clothes4happiness.controller;

import com.clothes4happiness.models.Colaborador;
import com.clothes4happiness.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {
    @Autowired
    private ColaboradorRepository repository;

    @GetMapping
    public List<Colaborador> getColaboradores() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getColaboradorById(@PathVariable Long id) {
        return repository.findById(id)
                .map(colaborador -> ResponseEntity.ok(colaborador))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Colaborador> createColaborador(@RequestBody Colaborador colaborador) {
        Colaborador savedColaborador = repository.save(colaborador);
        return ResponseEntity.ok(savedColaborador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> updateColaborador(@PathVariable Long id, @RequestBody Colaborador colaboradorDetails) {
        return repository.findById(id)
                .map(colaborador -> {
                    colaborador.setNome(colaboradorDetails.getNome());
                    colaborador.setCargo(colaboradorDetails.getCargo());
                    Colaborador updatedColaborador = repository.save(colaborador);
                    return ResponseEntity.ok(updatedColaborador);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColaborador(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}