package io.github.EduardoAraujoPires.sistema.ingressos.dto;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CompraResponseDTO {

    private Long id;
    private Long eventoId;
    private Integer quantidade;
    private LocalDateTime dataCompra;
    private StatusCompra status;
    private BigDecimal valorTotal;

    public static CompraResponseDTO fromEntity(Compra compra, BigDecimal precoUnitario){
        CompraResponseDTO dto = new CompraResponseDTO();
        dto.setId(compra.getId());
        dto.setEventoId(dto.getEventoId());
        dto.setDataCompra(compra.getDataCompra());
        dto.setStatus(compra.getStatus());
        dto.setQuantidade(dto.getQuantidade());
        dto.setValorTotal(precoUnitario.multiply(new BigDecimal(compra.getQuantidade())));
        return dto;
    }

}
