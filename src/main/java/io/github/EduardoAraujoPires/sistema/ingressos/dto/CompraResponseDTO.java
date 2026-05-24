package io.github.EduardoAraujoPires.sistema.ingressos.dto;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponseDTO {
    private UUID id;
    private UUID eventoId;
    private Integer quantidade;
    private LocalDateTime dataCompra;
    private StatusCompra status;
    private BigDecimal valorTotal;

    public static CompraResponseDTO fromEntity(Compra compra, BigDecimal precoUnitario) {
        CompraResponseDTO dto = new CompraResponseDTO();
        dto.setId(compra.getId());
        dto.setEventoId(compra.getEventoId());
        dto.setQuantidade(compra.getQuantidade());
        dto.setDataCompra(compra.getDataCompra());
        dto.setStatus(compra.getStatus());
        // Cálculo do valor total
        BigDecimal total = precoUnitario.multiply(BigDecimal.valueOf(compra.getQuantidade()));
        dto.setValorTotal(total);
        return dto;
    }
}

