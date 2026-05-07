package io.github.EduardoAraujoPires.sistema.ingressos.controller;

import io.github.EduardoAraujoPires.sistema.ingressos.expetion.ChaveDuplicadaExpetion;
import io.github.EduardoAraujoPires.sistema.ingressos.expetion.ErroChaveExpetion;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.service.EventoService;
import io.github.EduardoAraujoPires.sistema.ingressos.service.IdempotencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compras")
@AllArgsConstructor
public class CompraController {

    private final EventoService eventoService;
    private final IdempotencyService idempotencyService;

    @PostMapping
    public ResponseEntity<?> compra(@RequestParam ("eventoId") Long eventoId,
                                       @RequestParam Integer quantidade,
                                       @RequestHeader(value = "Idempotency-Key",required = true ) String chaveIdempotente){

        System.out.println("Requisição recebida - Chave " + eventoId);
        if(idempotencyService.jaProcessadas(chaveIdempotente, 60)){
            return ResponseEntity.ok().body("Requisição Duplicada detectada! chave: " + chaveIdempotente);
        }

        try {
            var compra = eventoService.processaCompra(eventoId, quantidade, chaveIdempotente);
            return ResponseEntity.ok(compra.getBody());
        } catch (ErroChaveExpetion e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
