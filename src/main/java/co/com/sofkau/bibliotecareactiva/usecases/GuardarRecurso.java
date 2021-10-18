package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface GuardarRecurso {
    Mono<String> apply(@Valid RecursoDTO recursoDTO);
}
