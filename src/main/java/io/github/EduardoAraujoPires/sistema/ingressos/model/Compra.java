package io.github.EduardoAraujoPires.sistema.ingressos.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_Compra", unique = true, nullable = false)
    private UUID id;

    @Column(name = "id_evento",  nullable = false)
    private UUID eventoId;

    @Column(name = "Quantidade_de_compra", nullable = false)
    private Integer quantidade;

    @CreatedDate
    @Column(name = "Data_de_compra", nullable = false)
    private LocalDateTime dataCompra;

    @Column(name = "Status_da_compra")
    private StatusCompra status;

    public Compra(UUID eventoId, Integer quantidade) {
        this.eventoId = eventoId;
        this.quantidade = quantidade;
    }

}
