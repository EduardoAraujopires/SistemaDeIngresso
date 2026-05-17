package io.github.EduardoAraujoPires.sistema.ingressos.common;

import io.github.EduardoAraujoPires.sistema.ingressos.expetion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExpetionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExpetionHandler.class);

    @ExceptionHandler(idNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleIdNaoEncontrado(idNaoEncontradoException ex) {
        return buildErrorResponse("NAO_ENCONTRADO", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChaveIdempotenteDuplicadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, String>> handleChaveDuplicada(ChaveIdempotenteDuplicadaException ex) {
        return buildErrorResponse("CHAVE_DUPLICADA", ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CompraExistenteException.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, String>> handleCompraJaRealizada(CompraExistenteException ex) {
        return buildErrorResponse("COMPRA_JA_REALIZADA", ex.getMessage(), HttpStatus.OK); // ou 200 com mensagem
    }

    @ExceptionHandler(ChaveIdempotenteInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleErroChave(ChaveIdempotenteInvalidaException ex) {
        return buildErrorResponse("ERRO_CHAVE", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, String>> handlerSemIngresso(EstoqueInsuficienteException e){
        Map<String, String> error = new HashMap<>();
        error.put("Codigo", "ESTOQUE_INSUFICIENTE");
        error.put("mensagem", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handlerValidation(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), e.getMessage())
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handlerMissingParameter(org.springframework.web.bind.MissingServletRequestParameterException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("erro", "PARAMETRO_AUSENTE");
        errors.put("mensagem", "Parâmetro obrigatório não informado" + e.getParameterName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handlerNotFound(RuntimeException e){
        if (e.getMessage().contains("não encontrado")){
            Map<String, String> errors = new HashMap<>();
            errors.put("codigo","RECURSO_NAO_ENCONTRADO");
            errors.put("mensagem", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return handlerGeneric(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handlerGeneric(Exception e){
        Map<String, String> errors = new HashMap<>();
        errors.put("codigo", "ERRO_INTERNO");
        errors.put("mensagem", "Ocorreu um erro inesperado. Tente Novamente mais tarde");
        log.error("Erro ao processar requisição: {}" , e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }




    private ResponseEntity<Map<String, String>> buildErrorResponse(String codigo, String mensagem, HttpStatus status) {
        Map<String, String> error = new HashMap<>();
        error.put("codigo", codigo);
        error.put("mensagem", mensagem);
        return ResponseEntity.status(status).body(error);
    }
}
