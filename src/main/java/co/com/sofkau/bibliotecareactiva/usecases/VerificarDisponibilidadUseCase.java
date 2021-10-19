package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public  class VerificarDisponibilidadUseCase  implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;

    public VerificarDisponibilidadUseCase(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }


    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "EL id no puede estar nulo");
        return recursoRepository.findById(id)
                .map(recurso ->
                        recurso.getCantidadDisponible() > recurso.getCantidadPrestada()
                        ? String.valueOf("El recurso " + recurso.getNombre() + "disponible y tiene " + (recurso.getCantidadDisponible()- recurso.getCantidadPrestada() + "Unidad(es) disponible(s)"))
                        : String.valueOf("El recurso " + recurso.getNombre() + " no esta diponible " + " fue prestado " + recurso.getFecha())
                        );
    }
}
