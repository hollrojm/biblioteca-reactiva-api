package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public  class EliminaRecursoUseCase implements Function<String, Mono<Void>> {

    private final RecursoRepository recursoRepository;

    public EliminaRecursoUseCase(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }


    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id es requerido");
        return recursoRepository.deleteById(id);
    }
}
