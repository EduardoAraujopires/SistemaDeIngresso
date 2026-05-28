package io.github.EduardoAraujoPires.sistema.ingressos.controller;

import io.github.EduardoAraujoPires.sistema.ingressos.controller.mapper.EventoMapper;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoResponseDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
@Tag(name = "Evento")
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
    @Operation(summary = "Salvar", description = "Salvar Evento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Evento Criado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Conflito,  erro de validação."),
            @ApiResponse(responseCode = "404", description = "Erro ao cadastrar, Compra não realizada."),
    })
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
