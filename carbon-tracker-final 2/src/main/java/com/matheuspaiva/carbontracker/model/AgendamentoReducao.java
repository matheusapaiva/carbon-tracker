package com.matheuspaiva.carbontracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "agendamentos")
public class AgendamentoReducao {

    @Id
    private String id;

    @NotNull(message = "Empresa é obrigatória")
    private String empresaId;

    private String empresaNome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    private LocalDate dataAgendada;

    private String status = "PENDENTE";

    public AgendamentoReducao() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmpresaId() { return empresaId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }

    public String getEmpresaNome() { return empresaNome; }
    public void setEmpresaNome(String empresaNome) { this.empresaNome = empresaNome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataAgendada() { return dataAgendada; }
    public void setDataAgendada(LocalDate dataAgendada) { this.dataAgendada = dataAgendada; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
