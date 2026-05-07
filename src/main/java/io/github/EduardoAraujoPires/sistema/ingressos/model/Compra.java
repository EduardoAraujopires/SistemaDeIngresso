package io.github.EduardoAraujoPires.sistema.ingressos.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Compra", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_evento",  nullable = false)
    private Long eventoId;

    @Column(name = "Quantidade_de_compra", nullable = false)
    private Integer quantidade;

    @CreatedDate
    @Column(name = "Data_de_compra", nullable = false)
    private LocalDateTime dataCompra;

    @Column(name = "Status_da_compra")
    private StatusCompra status;

    public Compra(Long eventoId, Integer quantidade) {
        this.eventoId = eventoId;
        this.quantidade = quantidade;
    }

    public Compra(){
    }
}
