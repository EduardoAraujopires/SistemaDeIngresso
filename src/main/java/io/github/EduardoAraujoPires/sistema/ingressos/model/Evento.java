package io.github.EduardoAraujoPires.sistema.ingressos.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_evento", nullable = false, unique = true)
    private UUID id;

    @Column(name = "nome_evento", nullable = false)
    private String nome;

    @Column(name = "capacidade_evento")
    private Integer capacidade;

    @Column(name = "ingressos_disponiveis")
    private Integer ingressosDisponiveis;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

}
