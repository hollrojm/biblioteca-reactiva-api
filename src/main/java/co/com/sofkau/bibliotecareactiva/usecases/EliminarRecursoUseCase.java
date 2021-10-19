package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

public final class EliminarRecursoUseCase implements Function<String, Mono<Void>> {

    private final RecursoRepository recursoRepository;

    public EliminarRecursoUseCase(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }


    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id es requerido");
        return recursoRepository.deleteById(id);
    }
}
