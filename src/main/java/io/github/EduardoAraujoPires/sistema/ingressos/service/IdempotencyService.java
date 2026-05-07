package io.github.EduardoAraujoPires.sistema.ingressos.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IdempotencyService {
    private final Map<String, Boolean> processados = new ConcurrentHashMap<>();

    public boolean jaProcessadas(String chave, int segundos) {

        if (processados.containsKey(chave)) {
            System.out.println("Requisição já solicitada, só um instante");
            return true;
        }

        // caso não tenha criado
        processados.put(chave, true);

        new Thread(() -> {
            try {
                Thread.sleep(segundos * 1000L);
                processados.remove(chave);
                System.out.println("Chave Removida: " + chave);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        System.out.println("Chave Registrada: " + chave);
        return false;
    }
}
