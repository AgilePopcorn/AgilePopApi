package com.agilepop;

import com.agilepop.model.Evento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveCriarEventoComSucesso() throws Exception {
        Evento evento = new Evento();
        evento.setNome("AgileConf");
        evento.setDescricao("Conferência de Agilidade");
        evento.setLocal("São Paulo");
        evento.setCategoria("Tecnologia");
        evento.setDataHora(LocalDateTime.now().plusDays(1));
        evento.setMaxParticipantes(100);

        mockMvc.perform(post("/api/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("AgileConf"))
                .andExpect(jsonPath("$.descricao").value("Conferência de Agilidade"));
    }

    @Test
    public void deveRetornarErroAoCriarEventoSemTitulo() throws Exception {
        Evento evento = new Evento();
        evento.setDescricao("Conferência de Agilidade");
        evento.setLocal("SP");
        evento.setCategoria("Tecnologia");
        evento.setDataHora(LocalDateTime.now().plusDays(1));
        evento.setMaxParticipantes(50);

        mockMvc.perform(post("/api/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("O título é obrigatório"));
    }

    @Test
    public void deveRetornarErroAoCriarEventoComDataPassada() throws Exception {
        Evento evento = new Evento();
        evento.setNome("AgileConf");
        evento.setDescricao("Evento passado");
        evento.setLocal("RJ");
        evento.setCategoria("Tecnologia");
        evento.setDataHora(LocalDateTime.now().minusDays(1));
        evento.setMaxParticipantes(10);

        mockMvc.perform(post("/api/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.dataHora").value("A data e hora devem ser futuras"));
    }

    @Test
    public void deveRetornarErroAoCriarEventoComDescricaoMuitoLonga() throws Exception {
        Evento evento = new Evento();
        evento.setNome("AgileConf");
        evento.setDescricao("A".repeat(1001));
        evento.setLocal("BH");
        evento.setCategoria("Tech");
        evento.setDataHora(LocalDateTime.now().plusDays(2));
        evento.setMaxParticipantes(30);

        mockMvc.perform(post("/api/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.descricao").value("A descrição não pode ter mais de 1000 caracteres"));
    }

    @Test
    public void deveRetornarErroAoCriarEventoComLocalVazio() throws Exception {
        Evento evento = new Evento();
        evento.setNome("AgileConf");
        evento.setDescricao("Evento sem local");
        evento.setLocal(""); // Local inválido
        evento.setCategoria("Tech");
        evento.setDataHora(LocalDateTime.now().plusDays(1));
        evento.setMaxParticipantes(30);

        mockMvc.perform(post("/api/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.local").value("O local é obrigatório"));
    }

    @Test
    public void deveListarEventos() throws Exception {
        mockMvc.perform(get("/api/eventos"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarErroSeTituloEstiverVazio() throws Exception {
        Evento evento = new Evento();
        evento.setNome(""); // inválido
        evento.setDescricao("Descrição válida");
        evento.setLocal("Online");
        evento.setCategoria("Educação");
        evento.setDataHora(LocalDateTime.now().plusDays(2));
        evento.setMaxParticipantes(100);

        mockMvc.perform(post("/api/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("O título é obrigatório"));
    }
}
