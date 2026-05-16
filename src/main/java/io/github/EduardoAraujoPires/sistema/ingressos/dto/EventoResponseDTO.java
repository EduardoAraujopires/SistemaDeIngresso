package io.github.EduardoAraujoPires.sistema.ingressos.dto;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventoResponseDTO {
    private Long id;
    private String nome;
    private Integer capacidade;
    private Integer ingressosDisponiveis;
    private BigDecimal preco;
    private Integer ingressosVendidos;

    public static EventoResponseDTO fromEntity(Evento evento){
        EventoResponseDTO dto = new EventoResponseDTO();
        dto.setId(evento.getId());
        dto.setCapacidade(evento.getCapacidade());
        dto.setNome(evento.getNome());
        dto.setPreco(evento.getPreco());
        dto.setIngressosDisponiveis(evento.getIngressosDisponiveis());
        dto.setIngressosVendidos(evento.getIngressosDisponiveis());

        dto.setIngressosVendidos(evento.getCapacidade() - evento.getIngressosDisponiveis());
        return dto;
    }
}
