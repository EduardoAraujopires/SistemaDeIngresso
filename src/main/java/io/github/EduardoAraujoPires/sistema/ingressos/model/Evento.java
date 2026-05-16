package io.github.EduardoAraujoPires.sistema.ingressos.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome_evento", nullable = false)
    private String nome;

    @Column(name = "capacidade_evento")
    private Integer capacidade;

    @Column(name = "ingressos_disponiveis")
    private Integer ingressosDisponiveis;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    public Evento(BigDecimal preco, String nome, Integer capacidade) {
        this.preco = preco;
        this.nome = nome;
        this.capacidade = capacidade;
    }
    public Evento(){
    }
}
