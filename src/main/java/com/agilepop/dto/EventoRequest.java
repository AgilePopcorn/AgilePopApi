package com.agilepop.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoRequest {
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 255, message = "O título não pode ter mais de 255 caracteres")
    private String nome;

    @NotBlank(message = "O local é obrigatório")
    private String local;

    @Future(message = "A data e hora devem ser futuras")
    @NotNull(message = "A data e hora são obrigatórios")
    private LocalDateTime dataHora;

    @Size(max = 1000, message = "A descrição não pode ter mais de 1000 caracteres")
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    @Min(value = 1, message = "O número mínimo de participantes é 1")
    private int maxParticipantes;

    private String imagemUrl;
    private String youtubeUrl;
}
