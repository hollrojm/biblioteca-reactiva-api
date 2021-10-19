package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public  class PrestarUseCase  implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final ModificarRecursoUseCase modificarRecursoUseCase;
    private final MapperUtils mapperUtils;

    public PrestarUseCase(RecursoRepository recursoRepository, MapperUtils mapperUtils) {
        this.recursoRepository = recursoRepository;
        this.mapperUtils = mapperUtils;
        this.modificarRecursoUseCase = new ModificarRecursoUseCase(recursoRepository,mapperUtils);
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id no puede ser nuulo");
        return recursoRepository.findById(id).flatMap(
                recurso -> {
                    if (recurso.getCantidadDisponible() > recurso.getCantidadPrestada()){
                        recurso.setFecha(LocalDate.now());
                        recurso.setCantidadPrestada(recurso.getCantidadPrestada() + 1);
                        return modificarRecursoUseCase.apply(mapperUtils.mapEntidadToRecurso().apply(recurso))
                                .thenReturn("El Recurso " + recurso.getNombre() + "ha sido prestado");

                    }
                    return Mono.just("No hay unidades disponibles para el recurso" + recurso.getNombre());
                }

        );
    }
}
