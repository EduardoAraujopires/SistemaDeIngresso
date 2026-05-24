package io.github.EduardoAraujoPires.sistema.ingressos.controller;

import io.github.EduardoAraujoPires.sistema.ingressos.controller.mapper.EventoMapper;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoResponseDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.service.EventoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {

    public final EventoService eventoService;
    private final EventoMapper mapper;

    @GetMapping
    public List<Evento> findAll(){
        return eventoService.findAll();
    }

    @GetMapping("/id")
    public Evento findByEventoId(@PathVariable("id") UUID id){
        return eventoService.findByEvento(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EventoRequestDTO dto,
                         @PathVariable("id") UUID id){
        return eventoService.update(id, dto);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody EventoRequestDTO dto){
        return eventoService.save(dto);

        }

    @GetMapping("/ingressos/{quantidade}")
    public List<Evento> findByIngressos(@PathVariable("quantidade") Integer quantidade){
        return eventoService.findByIngressos(quantidade);
    }

    @GetMapping("/buscar/{nome}")
    public List<Evento> findByNomeEvento(@PathVariable("nome") String nome){
        return eventoService.findByNome(nome);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        eventoService.deleteById(id);
    }
}
