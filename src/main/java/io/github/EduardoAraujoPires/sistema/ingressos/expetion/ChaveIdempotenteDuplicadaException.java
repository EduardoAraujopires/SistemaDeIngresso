package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class ChaveIdempotenteDuplicadaException extends RuntimeException {
    public ChaveIdempotenteDuplicadaException(String message) {
        super(message);
    }
}
