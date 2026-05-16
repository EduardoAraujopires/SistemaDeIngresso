package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class ChaveIdempotenteDuplicadaExpetion extends RuntimeException {
    public ChaveIdempotenteDuplicadaExpetion(String message) {
        super(message);
    }
}
