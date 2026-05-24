package io.github.EduardoAraujoPires.sistema.ingressos.service;

import io.github.EduardoAraujoPires.sistema.ingressos.controller.mapper.EventoMapper;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoResponseDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.expetion.idNaoEncontradoException;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoMapper mapper;

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Evento findByEvento(UUID id) {
        return eventoRepository.findById(id).orElseThrow(()-> new idNaoEncontradoException("Não foi possivel encontrar o id"));

    }

    @Transactional
    public ResponseEntity<?> save(EventoRequestDTO dto) {
         Evento evento = mapper.toEntity(dto);
        evento.setIngressosDisponiveis(dto.getCapacidade());
        eventoRepository.save(evento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(evento.getId()).toUri();
        return ResponseEntity.created(uri).body(evento);
    }

    @Transactional
    public ResponseEntity<?> update(UUID id, EventoRequestDTO dto) {
        Evento eventoId = eventoRepository.findById(id).orElseThrow(() -> new idNaoEncontradoException("Id não encontrado"));
        if (id.equals(eventoId.getId())) {
            eventoId.setNome(dto.getNome());
            eventoId.setPreco(dto.getPreco());
            eventoId.setCapacidade(dto.getCapacidade());

            int novosIgressos = eventoId.getCapacidade() - eventoId.getIngressosDisponiveis();
            eventoId.setIngressosDisponiveis(dto.getCapacidade() - novosIgressos);
            eventoRepository.save(eventoId);
            return ResponseEntity.status(HttpStatus.OK).body(eventoId);

        } else {
            throw new idNaoEncontradoException("Id não encontrado");
        }
    }

    @Transactional
    public void deleteById(UUID id){
        Evento eventoId = eventoRepository.findById(id).orElseThrow(() -> new idNaoEncontradoException("Id não encontrado"));
        if(eventoId.getId().equals(id)) {
            eventoRepository.deleteById(id);
        }
    }

    public List<Evento> findByIngressos(Integer quantide){
        return eventoRepository.findByIngressosDisponiveisGreaterThan(quantide);
    }

    public List<Evento> findByNome(String nome){
        return eventoRepository.findByNomeContainsIgnoreCase(nome);
    }

}
