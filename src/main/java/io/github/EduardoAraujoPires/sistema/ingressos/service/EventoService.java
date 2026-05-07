package io.github.EduardoAraujoPires.sistema.ingressos.service;

import io.github.EduardoAraujoPires.sistema.ingressos.expetion.IdWithLockNaoEncontradoExpetion;
import io.github.EduardoAraujoPires.sistema.ingressos.expetion.SemIngressoDisponivelExpetion;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.repository.CompraRepository;
import io.github.EduardoAraujoPires.sistema.ingressos.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final CompraRepository compraRepository;

    @Transactional
    public ResponseEntity<?>processaCompra(Long eventoId, Integer quantidade, String chaveIdepotente){

        System.out.println("Processando compra...");
        Evento evento = (Evento) eventoRepository.findByIdWithLock(eventoId)
                .orElseThrow(()-> new IdWithLockNaoEncontradoExpetion("Evento não encontrado"));

        if (evento.getIngressosDisponiveis() < quantidade){
            throw new SemIngressoDisponivelExpetion("Só temos " + evento.getIngressosDisponiveis() + " ingressos disponíveis.");
        }

        evento.setIngressosDisponiveis(evento.getIngressosDisponiveis() - quantidade);
        eventoRepository.save(evento);

        Compra compra = new Compra(eventoId, quantidade);
        compra = compraRepository.save(compra);


        return ResponseEntity.ok().body("Compra realizada com sucesso, Restante de ingressos: "+ evento.getIngressosDisponiveis());


    }

    public Evento save(Evento evento){
        return eventoRepository.save(evento);
    }
}
