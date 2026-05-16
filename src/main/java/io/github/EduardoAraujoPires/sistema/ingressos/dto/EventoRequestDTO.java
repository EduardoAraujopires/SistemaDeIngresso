package io.github.EduardoAraujoPires.sistema.ingressos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EventoRequestDTO {

    @NotBlank(message = "Campo Obrigatório!")
    private String nome;

    @Min(value = 1, message = "Capacidade mínima é 1 pessoa")
    private Integer capacidade;

    @Positive(message = "Preço deve ser positivo")
    private BigDecimal preco;
}
