package io.github.EduardoAraujoPires.sistema.ingressos.common;

import io.github.EduardoAraujoPires.sistema.ingressos.expetion.IdWithLockNaoEncontradoExpetion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExpetionHandler {

//    @ExceptionHandler(IdWithLockNaoEncontradoExpetion.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public
}
