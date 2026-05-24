package io.github.EduardoAraujoPires.sistema.ingressos.repository;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface EventoRepository extends JpaRepository<Evento, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Evento e where e.id= :id")
    Optional<Evento> findByIdWithLock(@Param("id") UUID id);

    List<Evento> findByIngressosDisponiveisGreaterThan(Integer quantidade);
    List<Evento> findByNomeContainsIgnoreCase(String nome);
}
