package com.matheuspaiva.carbontracker.controller;

import com.matheuspaiva.carbontracker.model.AgendamentoReducao;
import com.matheuspaiva.carbontracker.repository.AgendamentoReducaoRepository;
import com.matheuspaiva.carbontracker.repository.EmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento-reducao-carbono")
public class AgendamentoReducaoController {

    @Autowired
    private AgendamentoReducaoRepository agendamentoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public List<AgendamentoReducao> listar() {
        return agendamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoReducao> buscarPorId(@PathVariable String id) {
        return agendamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AgendamentoReducao> criar(@Valid @RequestBody AgendamentoReducao agendamento) {
        empresaRepository.findById(agendamento.getEmpresaId()).ifPresent(e ->
            agendamento.setEmpresaNome(e.getNome())
        );
        AgendamentoReducao salvo = agendamentoRepository.save(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoReducao> atualizar(@PathVariable String id,
                                                         @Valid @RequestBody AgendamentoReducao agendamento) {
        return agendamentoRepository.findById(id).map(existente -> {
            existente.setDescricao(agendamento.getDescricao());
            existente.setDataAgendada(agendamento.getDataAgendada());
            existente.setStatus(agendamento.getStatus());
            return ResponseEntity.ok(agendamentoRepository.save(existente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable String id) {
        return agendamentoRepository.findById(id).map(a -> {
            a.setStatus("CANCELADO");
            agendamentoRepository.save(a);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
