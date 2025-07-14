package com.agilepop.dto;
 
import java.time.LocalDateTime;

import lombok.Data;


@Data
public class EventoResponse {
    private Long id;
    private String nome;
    private String local;    
    private LocalDateTime dataHora;
    private String descricao;
    private String categoria;
    private int maxParticipantes;
    private String imagemUrl;
    private String youtubeUrl;
    // Getters e Setters
}
