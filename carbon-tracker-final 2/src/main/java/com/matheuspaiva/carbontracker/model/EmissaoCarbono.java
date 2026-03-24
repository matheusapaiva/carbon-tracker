package com.matheuspaiva.carbontracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Document(collection = "emissoes")
public class EmissaoCarbono {

    @Id
    private String id;

    @NotNull(message = "Empresa é obrigatória")
    private String empresaId;

    private String empresaNome;

    @NotNull
    @Positive(message = "Quantidade de emissão deve ser positiva")
    private Double quantidadeEmissao;

    private LocalDate dataEmissao;

    private String observacao;

    public EmissaoCarbono() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmpresaId() { return empresaId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }

    public String getEmpresaNome() { return empresaNome; }
    public void setEmpresaNome(String empresaNome) { this.empresaNome = empresaNome; }

    public Double getQuantidadeEmissao() { return quantidadeEmissao; }
    public void setQuantidadeEmissao(Double quantidadeEmissao) { this.quantidadeEmissao = quantidadeEmissao; }

    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
