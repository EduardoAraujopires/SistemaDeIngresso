package io.github.EduardoAraujoPires.sistema.ingressos.controller;

import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.service.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {

    public final EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> save(@RequestBody Evento evento){
        return ResponseEntity.ok().body(eventoService.save(evento));
    }
}
