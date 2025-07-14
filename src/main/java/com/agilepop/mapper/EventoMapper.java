package com.agilepop.mapper;

import com.agilepop.dto.EventoRequest;
import com.agilepop.dto.EventoResponse;
import com.agilepop.model.Evento;

public class EventoMapper {

    public static Evento toEntity(EventoRequest dto) {
        Evento evento = new Evento();
        evento.setNome(dto.getNome());
        evento.setLocal(dto.getLocal());
        evento.setDataHora(dto.getDataHora());
        evento.setDescricao(dto.getDescricao());
        evento.setCategoria(dto.getCategoria());
        evento.setMaxParticipantes(dto.getMaxParticipantes());
        evento.setImagemUrl(dto.getImagemUrl());
        evento.setYoutubeUrl(dto.getYoutubeUrl());
        return evento;
    }

    public static EventoResponse toResponse(Evento entity) {
        EventoResponse response = new EventoResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setLocal(entity.getLocal());
        response.setDataHora(entity.getDataHora());
        response.setDescricao(entity.getDescricao());
        response.setCategoria(entity.getCategoria());
        response.setMaxParticipantes(entity.getMaxParticipantes());
        response.setImagemUrl(entity.getImagemUrl());
        response.setYoutubeUrl(entity.getYoutubeUrl());
        return response;
    }
}
