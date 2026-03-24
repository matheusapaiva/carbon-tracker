package com.matheuspaiva.carbontracker;

import com.matheuspaiva.carbontracker.model.Empresa;
import com.matheuspaiva.carbontracker.repository.EmpresaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        empresaRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void deveListarEmpresas() throws Exception {
        mockMvc.perform(get("/empresas-sustentaveis"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void deveCadastrarEmpresa() throws Exception {
        Empresa empresa = new Empresa("EcoTech", "Tecnologia");

        mockMvc.perform(post("/empresas-sustentaveis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("EcoTech"))
                .andExpect(jsonPath("$.setor").value("Tecnologia"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void deveRetornarErroSemNome() throws Exception {
        Empresa empresa = new Empresa();
        empresa.setSetor("Tecnologia");

        mockMvc.perform(post("/empresas-sustentaveis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar401SemAutenticacao() throws Exception {
        mockMvc.perform(get("/empresas-sustentaveis"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void deveBuscarEmpresaPorId() throws Exception {
        Empresa empresa = empresaRepository.save(new Empresa("GreenCorp", "Energia"));

        mockMvc.perform(get("/empresas-sustentaveis/" + empresa.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("GreenCorp"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void deveRetornar404ParaEmpresaInexistente() throws Exception {
        mockMvc.perform(get("/empresas-sustentaveis/id-que-nao-existe"))
                .andExpect(status().isNotFound());
    }
}
