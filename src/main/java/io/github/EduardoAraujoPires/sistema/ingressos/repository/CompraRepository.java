package io.github.EduardoAraujoPires.sistema.ingressos.repository;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
