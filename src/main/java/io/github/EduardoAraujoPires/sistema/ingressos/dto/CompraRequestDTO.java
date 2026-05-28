package io.github.EduardoAraujoPires.sistema.ingressos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Compra")
public class CompraRequestDTO {

    @NotNull(message = "O id do evento é obrigatório")
    @JsonProperty(value = "eventoId", required = false)
    @Schema(name = "Id Evento")
    private UUID eventoId;

    @NotNull(message = "Quantidade é obrigatório")
    @Min(value = 1, message = "Quantidade mínima é 1 ingresso")
    @Max(value = 10, message = "Quantidade máxima é 10 ingressos")
    @Schema(name = "Quantidade")
    private Integer quantidade;

    @Schema(name = "Status dá compra")
    @NotNull(message = "Campo obrigatório")
    private StatusCompra statusCompra;
}
