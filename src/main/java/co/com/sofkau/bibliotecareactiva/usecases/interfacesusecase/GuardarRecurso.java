package co.com.sofkau.bibliotecareactiva.usecases.interfacesusecase;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface GuardarRecurso {
    Mono<RecursoDTO> apply(@Valid RecursoDTO recursoDTO);
}
