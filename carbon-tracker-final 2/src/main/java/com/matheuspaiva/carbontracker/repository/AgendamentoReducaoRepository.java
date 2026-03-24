package com.matheuspaiva.carbontracker.repository;

import com.matheuspaiva.carbontracker.model.AgendamentoReducao;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AgendamentoReducaoRepository extends MongoRepository<AgendamentoReducao, String> {
    List<AgendamentoReducao> findByEmpresaId(String empresaId);
    List<AgendamentoReducao> findByStatus(String status);
}
