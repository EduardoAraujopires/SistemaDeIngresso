package io.github.EduardoAraujoPires.sistema.ingressos.controller;

import io.github.EduardoAraujoPires.sistema.ingressos.dto.CompraRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.CompraResponseDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import io.github.EduardoAraujoPires.sistema.ingressos.service.CompraService;
import io.github.EduardoAraujoPires.sistema.ingressos.service.EventoService;
import io.github.EduardoAraujoPires.sistema.ingressos.service.IdempotencyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@AllArgsConstructor
public class CompraController {

    private final CompraService compraService;
    private final IdempotencyService idempotencyService;
    private final EventoService eventoService;
    private static final Logger log = LoggerFactory.getLogger(CompraController.class);

    @PostMapping
    public ResponseEntity<?> compra(@Valid @RequestBody CompraRequestDTO dto,
                                    @RequestHeader(value = "Idempotency-Key",
                                            required = true) String chaveIdempotente) {

        log.info("Requisição recebida - Chave {}", dto.getEventoId());
        if (idempotencyService.jaProcessadas(chaveIdempotente, 60)) {
            return ResponseEntity.ok().body("Requisição Duplicada detectada! chave: " + chaveIdempotente);
        }

            Compra compra = compraService.processaCompra(dto, chaveIdempotente);
            Evento evento = eventoService.findByEvento(dto.getEventoId());

            CompraResponseDTO compraResponseDTO = CompraResponseDTO.fromEntity(compra, evento.getPreco());
            log.info("Compra Realizada com Sucesso - ID: {}", compra.getId());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getEventoId()).toUri();
            return ResponseEntity.created(uri).body(compraResponseDTO);

    }

    @GetMapping
    public List<Compra> findAll() {
        return compraService.findAll();
    }

    @PostMapping("/devolver/{id}")
    public void devolveIngresso(@PathVariable("id") Long id) {
        compraService.devolveCompra(id);
    }

    @GetMapping("/{id}")
    public Compra findByCompraId(@PathVariable("id") Long id) {
        return compraService.findByCompraId(id);
    }

    @PutMapping("/update/{id}")
    public Compra update(@Valid @RequestBody CompraRequestDTO dto, @PathVariable("id") Long id) {
        return compraService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        compraService.deleteById(id);
    }

    @GetMapping("/buscarStatus/{status}")
    public List<Compra> findByStatus(@PathVariable("status") StatusCompra status) {
        return compraService.findByStatus(status);
    }

    @GetMapping("/buscarEventoId/{id}")
    public List<Compra> findByEventoId(@PathVariable("id") Long id) {
        return compraService.findByEventoId(id);
    }
}
