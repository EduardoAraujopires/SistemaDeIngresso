package io.github.EduardoAraujoPires.sistema.ingressos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CompraRequestDTO {

    @NotNull(message = "O id do evento é obrigatório")
    @JsonProperty(value = "eventoId", required = false)
    private UUID eventoId;

    @NotNull(message = "Quantidade é obrigatório")
    @Min(value = 1, message = "Quantidade mínima é 1 ingresso")
    @Max(value = 10, message = "Quantidade máxima é 10 ingressos")
    private Integer quantidade;


    @NotNull(message = "Campo obrigatório")
    private StatusCompra statusCompra;
}
