package io.github.EduardoAraujoPires.sistema.ingressos.controller.mapper;

import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoResponseDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    Evento toEntity(EventoRequestDTO dto);
    EventoResponseDTO toDTO(Evento evento);


}

