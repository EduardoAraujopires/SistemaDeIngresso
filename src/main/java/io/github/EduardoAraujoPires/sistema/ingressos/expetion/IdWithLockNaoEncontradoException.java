package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class IdWithLockNaoEncontradoException extends RuntimeException {
    public IdWithLockNaoEncontradoException(String message) {
        super(message);
    }
}
