package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class CompraExistenteException extends RuntimeException {
    public CompraExistenteException(String message) {
        super(message);
    }
}
