package io.github.EduardoAraujoPires.sistema.ingressos.expetion;

public class SemIngressoDisponivelExpetion extends RuntimeException{
    public SemIngressoDisponivelExpetion(String msg){
        super(msg);
    }
}
