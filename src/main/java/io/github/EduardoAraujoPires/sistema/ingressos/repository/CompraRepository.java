package io.github.EduardoAraujoPires.sistema.ingressos.repository;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, UUID> {

    List<Compra> findByEventoId (UUID id);

    List<Compra> findByStatus(StatusCompra status);

}
