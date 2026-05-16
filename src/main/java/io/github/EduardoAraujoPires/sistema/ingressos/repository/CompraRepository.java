package io.github.EduardoAraujoPires.sistema.ingressos.repository;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findByEventoId (Long id);

    List<Compra> findByStatus(StatusCompra status);

}
