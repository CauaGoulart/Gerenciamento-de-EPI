package com.clothes4happiness.controller;

import com.clothes4happiness.models.ControleEPI;
import com.clothes4happiness.repository.ControleEPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controle-epi")
public class ControleEPIController {
    @Autowired
    private ControleEPIRepository repository;

    @GetMapping
    public List<ControleEPI> getControleEPI() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControleEPI> getControleEPIById(@PathVariable Long id) {
        return repository.findById(id)
                .map(controleEPI -> ResponseEntity.ok(controleEPI))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ControleEPI> createControleEPI(@RequestBody ControleEPI controleEPI) {
        ControleEPI savedControleEPI = repository.save(controleEPI);
        return ResponseEntity.ok(savedControleEPI);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ControleEPI> updateControleEPI(@PathVariable Long id, @RequestBody ControleEPI controleEpiDetails) {
        return repository.findById(id)
                .map(controleEPI -> {
                    controleEPI.setEquipamento(controleEpiDetails.getEquipamento());
                    controleEPI.setColaborador(controleEpiDetails.getColaborador());
                    controleEPI.setDataEmprestimo(controleEpiDetails.getDataEmprestimo());
                    controleEPI.setDataPrevistaDevolucao(controleEpiDetails.getDataPrevistaDevolucao());
                    controleEPI.setStatus(controleEpiDetails.getStatus());
                    controleEPI.setCondicoes(controleEpiDetails.getCondicoes());
                    controleEPI.setDataDevolucao(controleEpiDetails.getDataDevolucao());
                    controleEPI.setObservacao(controleEpiDetails.getObservacao());
                    ControleEPI updatedControleEPI = repository.save(controleEPI);
                    return ResponseEntity.ok(updatedControleEPI);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteControleEPI(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
