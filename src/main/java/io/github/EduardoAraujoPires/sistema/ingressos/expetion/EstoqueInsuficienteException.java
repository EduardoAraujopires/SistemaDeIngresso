package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class EstoqueInsuficienteException extends RuntimeException{
    public EstoqueInsuficienteException(String msg){
        super(msg);
    }
}
