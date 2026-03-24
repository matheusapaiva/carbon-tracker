package com.matheuspaiva.carbontracker.repository;

import com.matheuspaiva.carbontracker.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
    List<Empresa> findBySetor(String setor);
    boolean existsByNome(String nome);
}
