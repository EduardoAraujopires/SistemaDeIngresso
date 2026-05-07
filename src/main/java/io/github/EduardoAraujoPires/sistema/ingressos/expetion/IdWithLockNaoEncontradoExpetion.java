package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class IdWithLockNaoEncontradoExpetion extends RuntimeException {
    public IdWithLockNaoEncontradoExpetion(String message) {
        super(message);
    }
}
