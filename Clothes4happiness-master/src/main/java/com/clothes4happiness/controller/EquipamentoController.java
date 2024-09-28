package com.clothes4happiness.controller;

import com.clothes4happiness.models.Equipamento;
import com.clothes4happiness.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {
    @Autowired
    private EquipamentoRepository repository;

    @GetMapping
    public List<Equipamento> getEquipamentos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable Long id) {
        return repository.findById(id)
                .map(equipamento -> ResponseEntity.ok(equipamento))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipamento> createEquipamento(@RequestBody Equipamento equipamento) {
        Equipamento savedEquipamento = repository.save(equipamento);
        return ResponseEntity.ok(savedEquipamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> updateEquipamento(@PathVariable Long id, @RequestBody Equipamento equipamentoDetails) {
        return repository.findById(id)
                .map(equipamento -> {
                    equipamento.setNome(equipamentoDetails.getNome());
                    equipamento.setTipo(equipamentoDetails.getTipo());
                    equipamento.setStatus(equipamentoDetails.getStatus());
                    Equipamento updatedEquipamento = repository.save(equipamento);
                    return ResponseEntity.ok(updatedEquipamento);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}