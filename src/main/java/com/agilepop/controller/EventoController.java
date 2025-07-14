package com.agilepop.controller;

import com.agilepop.exception.ResourceNotFoundException;
import com.agilepop.model.Evento;
import com.agilepop.repository.EventoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Gerenciamento de eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;


    @PostMapping
    @Operation(summary = "Criar novo evento", description = "Cria um novo evento com os dados fornecidos")
    public ResponseEntity<Evento> criar(@Valid @RequestBody Evento evento) {
        Evento salvo = eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evento por ID", description = "Retorna um evento específico pelo seu ID")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento com ID " + id + " não encontrado"));
        return ResponseEntity.ok(evento);
    }

    @GetMapping
    @Operation(summary = "Listar todos os eventos", description = "Retorna uma lista de todos os eventos cadastrados")
    public ResponseEntity<List<Evento>> listarTodos() {
        List<Evento> eventos = eventoRepository.findAll();
        return ResponseEntity.ok(eventos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar evento", description = "Atualiza um evento existente com os dados fornecidos")
    public ResponseEntity<Evento> atualizar(@PathVariable Long id, @Valid @RequestBody Evento eventoAtualizado) {
        Evento eventoExistente = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento com ID " + id + " não encontrado"));

        eventoExistente.setNome(eventoAtualizado.getNome());
        eventoExistente.setLocal(eventoAtualizado.getLocal());
        eventoExistente.setDataHora(eventoAtualizado.getDataHora());
        eventoExistente.setDescricao(eventoAtualizado.getDescricao());
        eventoExistente.setCategoria(eventoAtualizado.getCategoria());
        eventoExistente.setMaxParticipantes(eventoAtualizado.getMaxParticipantes());
        eventoExistente.setImagemUrl(eventoAtualizado.getImagemUrl());
        eventoExistente.setYoutubeUrl(eventoAtualizado.getYoutubeUrl());

        Evento atualizado = eventoRepository.save(eventoExistente);
        return ResponseEntity.ok(atualizado);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar evento", description = "Remove um evento existente pelo seu ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento com ID " + id + " não encontrado"));

        eventoRepository.delete(evento);
        return ResponseEntity.noContent().build();
    }
}