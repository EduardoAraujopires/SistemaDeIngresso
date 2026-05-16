package io.github.EduardoAraujoPires.sistema.ingressos.controller;

import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoResponseDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.service.EventoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {

    public final EventoService eventoService;

    @GetMapping
    public List<Evento> findAll(){
        return eventoService.findAll();
    }

    @GetMapping("/id")
    public Evento findByEventoId(@PathVariable("id") Long id){
        return eventoService.findByEvento(id);
    }

    @PutMapping("/update/{id}")
    public Evento update(@Valid @RequestBody EventoRequestDTO dto,
                         @PathVariable("id") Long id){
        return eventoService.update(id, dto);
    }

    @PostMapping
    public Evento save(@Valid @RequestBody EventoRequestDTO dto){
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
    public void delete(@PathVariable("id") Long id){
        eventoService.deleteById(id);
    }
}
