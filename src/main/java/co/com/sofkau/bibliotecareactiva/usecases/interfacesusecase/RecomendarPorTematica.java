package co.com.sofkau.bibliotecareactiva.usecases.interfacesusecase;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@FunctionalInterface
public interface RecomendarPorTematica {
    Flux<RecursoDTO> get(@Valid String tematica);
}
