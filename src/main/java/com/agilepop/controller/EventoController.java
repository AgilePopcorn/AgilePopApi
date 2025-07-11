package com.agilepop.controller;

import com.agilepop.model.Evento;
import com.agilepop.repository.EventoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Gerenciamento de eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    @Operation(summary = "Listar todos os eventos", description = "Retorna uma lista de todos os eventos cadastrados")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*")
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evento por ID", description = "Retorna um evento específico pelo seu ID")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo evento", description = "Cria um novo evento com os dados fornecidos")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Evento> criar(@Valid @RequestBody Evento evento) {
        Evento salvo = eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar evento", description = "Atualiza um evento existente com os dados fornecidos")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Evento> atualizar(@PathVariable Long id, @Valid @RequestBody Evento eventoAtualizado) {
        return eventoRepository.findById(id)
                .map(eventoExistente -> {
                    // Atualiza campo a campo
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
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar evento", description = "Remove um evento existente pelo seu ID")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return eventoRepository.findById(id)
                .map(evento -> {
                    eventoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
