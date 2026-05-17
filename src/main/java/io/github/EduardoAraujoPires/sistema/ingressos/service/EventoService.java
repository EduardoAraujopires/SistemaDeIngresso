package io.github.EduardoAraujoPires.sistema.ingressos.service;

import io.github.EduardoAraujoPires.sistema.ingressos.dto.EventoRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.expetion.idNaoEncontradoException;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Evento findByEvento(Long id) {
        return eventoRepository.findById(id).orElseThrow(() -> new idNaoEncontradoException("Id não encontrado"));
    }

    @Transactional
    public Evento save(EventoRequestDTO dto) {
         Evento evento = new Evento();
         evento.setNome(dto.getNome());
         evento.setPreco(dto.getPreco());
         evento.setCapacidade(dto.getCapacidade());
         evento.setIngressosDisponiveis(dto.getCapacidade());

        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento update(Long id, EventoRequestDTO dto) {
        Evento eventoId = eventoRepository.findById(id).orElseThrow(() -> new idNaoEncontradoException("Id não encontrado"));
        if (id.equals(eventoId.getId())) {
            eventoId.setNome(dto.getNome());
            eventoId.setPreco(dto.getPreco());
            eventoId.setCapacidade(dto.getCapacidade());

            int novosIgressos = eventoId.getCapacidade() - eventoId.getIngressosDisponiveis();
            eventoId.setIngressosDisponiveis(dto.getCapacidade() - novosIgressos);
            return eventoRepository.save(eventoId);

        }
        return null;

    }

    @Transactional
    public void deleteById(Long id){
        eventoRepository.deleteById(id);
    }

    public List<Evento> findByIngressos(Integer quantide){
        return eventoRepository.findByIngressosDisponiveisGreaterThan(quantide);
    }

    public List<Evento> findByNome(String nome){
        return eventoRepository.findByNomeContainsIgnoreCase(nome);
    }

}
