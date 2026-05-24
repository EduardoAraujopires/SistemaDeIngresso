package io.github.EduardoAraujoPires.sistema.ingressos.dto;

import io.github.EduardoAraujoPires.sistema.ingressos.controller.mapper.EventoMapper;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Getter
@Setter
public class EventoResponseDTO {
    private UUID id;
    private String nome;
    private Integer capacidade;
    private Integer ingressosDisponiveis;
    private BigDecimal preco;
    private Integer ingressosVendidos;
    private static EventoMapper mapper;

    public static EventoResponseDTO fromEntity(Evento evento) {
        EventoResponseDTO dto =  mapper.toDTO(evento);

        // Calcula ingressos vendidos
        dto.setIngressosVendidos(evento.getCapacidade() - evento.getIngressosDisponiveis());
        return dto;
    }
}
