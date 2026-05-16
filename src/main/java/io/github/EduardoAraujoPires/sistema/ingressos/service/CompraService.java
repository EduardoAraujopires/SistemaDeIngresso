package io.github.EduardoAraujoPires.sistema.ingressos.service;

import io.github.EduardoAraujoPires.sistema.ingressos.dto.CompraRequestDTO;
import io.github.EduardoAraujoPires.sistema.ingressos.expetion.IdWithLockNaoEncontradoExpetion;
import io.github.EduardoAraujoPires.sistema.ingressos.expetion.SemIngressoDisponivelExpetion;
import io.github.EduardoAraujoPires.sistema.ingressos.expetion.idNaoEncontradoExpetion;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Compra;
import io.github.EduardoAraujoPires.sistema.ingressos.model.Evento;
import io.github.EduardoAraujoPires.sistema.ingressos.model.StatusCompra;
import io.github.EduardoAraujoPires.sistema.ingressos.repository.CompraRepository;
import io.github.EduardoAraujoPires.sistema.ingressos.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final EventoRepository eventoRepository;

    private static final Logger log =  LoggerFactory.getLogger(CompraService.class);

    @Transactional
    public Compra processaCompra(CompraRequestDTO dto, String chaveIdepotente) {

        log.info(" Processando compra - Chave: {}, Evento: {}, Qtd: {}",
                chaveIdepotente, dto.getEventoId(), dto.getQuantidade());

        try {
            log.debug("Buscando chave com Luck - ID: {}", dto.getEventoId());
        Evento evento = (Evento) eventoRepository.findByIdWithLock(dto.getEventoId())
                .orElseThrow(() -> new IdWithLockNaoEncontradoExpetion("Evento não encontrado"));


        log.debug("Evento encotrado - Nome: {}, Disponivel: {}"
                ,evento.getNome()
                , evento.getIngressosDisponiveis());

            if (evento.getIngressosDisponiveis() < dto.getQuantidade()) {
                log.warn("Estoque esgotado - Solicitado: {}, Disponíveis: {}",
                        dto.getQuantidade(), evento.getIngressosDisponiveis());
                dto.setStatusCompra(StatusCompra.CANCELADA);
                throw new SemIngressoDisponivelExpetion("Só temos " + evento.getIngressosDisponiveis() + " ingressos disponíveis.");
            }


            evento.setIngressosDisponiveis(evento.getIngressosDisponiveis() - dto.getQuantidade());
            eventoRepository.save(evento);

            Compra compra = new Compra(dto.getEventoId(), dto.getQuantidade());
            compra.setStatus(StatusCompra.CONFIRMADA);
            compra = compraRepository.save(compra);


            log.info("Compra realizada com sucesso, Restante de ingressos: {}" , evento.getIngressosDisponiveis());
            return compra;

        } catch (Exception e) {
            log.error("Erro ao processar a compra - Evento: {}, Chave: {}",
                    dto.getEventoId(), chaveIdepotente);
            dto.setStatusCompra(StatusCompra.CANCELADA);
            throw e;
        }
    }

    @Transactional
    public void devolveCompra(Long id) {
        Compra compra = compraRepository.findById(id).orElseThrow(() -> new idNaoEncontradoExpetion("Id não existe"));

        Evento evento = eventoRepository.findByIdWithLock(compra.getEventoId()).orElseThrow(() -> new IdWithLockNaoEncontradoExpetion("Nao encontrado!"));

        evento.setIngressosDisponiveis(
                evento.getIngressosDisponiveis() + compra.getQuantidade()
        );

        compraRepository.save(compra);
        eventoRepository.save(evento);

    }

    public List<Compra> findAll(){
        return compraRepository.findAll();
    }

    public Compra findByCompraId(Long id){
        return compraRepository.findById(id).orElseThrow(()-> new idNaoEncontradoExpetion("Id não encontrado!"));
    }

    @Transactional
    public Compra update(CompraRequestDTO dto, Long id){
        Compra compra = compraRepository.findById(id).orElseThrow(()-> new idNaoEncontradoExpetion("Id não encontrado!"));
         if(id.equals(compra.getId())){
             compra.setStatus(dto.getStatusCompra());
             compra.setQuantidade(dto.getQuantidade());
             return compra;
         }

         return null;
    }

    @Transactional
    public void deleteById(Long id){
        Compra compra;
              compra = new Compra();
        compra.setStatus(StatusCompra.CANCELADA);

        compraRepository.deleteById(id);
    }

    public List<Compra> findByStatus(StatusCompra statusCompra){
        return compraRepository.findByStatus(statusCompra);
    }

    public List<Compra> findByEventoId(Long id){
        return compraRepository.findByEventoId(id);
    }
}
