package com.matheuspaiva.carbontracker.controller;

import com.matheuspaiva.carbontracker.model.EmissaoCarbono;
import com.matheuspaiva.carbontracker.model.Empresa;
import com.matheuspaiva.carbontracker.repository.EmissaoCarbonoRepository;
import com.matheuspaiva.carbontracker.repository.EmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emissoes-carbono")
public class EmissaoCarbonoController {

    @Autowired
    private EmissaoCarbonoRepository emissaoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public List<EmissaoCarbono> listar() {
        return emissaoRepository.findAll();
    }

    @GetMapping("/historico/{empresaId}")
    public ResponseEntity<List<EmissaoCarbono>> historicoPorEmpresa(@PathVariable String empresaId) {
        List<EmissaoCarbono> historico = emissaoRepository.findByEmpresaIdOrderByDataEmissaoDesc(empresaId);
        return ResponseEntity.ok(historico);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody EmissaoCarbono emissao) {
        if (emissao.getDataEmissao() == null) {
            emissao.setDataEmissao(LocalDate.now());
        }
        empresaRepository.findById(emissao.getEmpresaId()).ifPresent(e ->
            emissao.setEmpresaNome(e.getNome())
        );
        EmissaoCarbono salva = emissaoRepository.save(emissao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if (!emissaoRepository.existsById(id)) return ResponseEntity.notFound().build();
        emissaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
