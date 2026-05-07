package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class ChaveDuplicadaExpetion extends RuntimeException {
    public ChaveDuplicadaExpetion(String message) {
        super(message);
    }
}
