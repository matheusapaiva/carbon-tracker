package com.matheuspaiva.carbontracker.repository;

import com.matheuspaiva.carbontracker.model.EmissaoCarbono;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EmissaoCarbonoRepository extends MongoRepository<EmissaoCarbono, String> {
    List<EmissaoCarbono> findByEmpresaId(String empresaId);
    List<EmissaoCarbono> findByEmpresaIdOrderByDataEmissaoDesc(String empresaId);
}
