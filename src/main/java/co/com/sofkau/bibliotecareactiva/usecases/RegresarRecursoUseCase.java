package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

public final class RegresarRecursoUseCase implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final ModificarRecursoUseCase modificarRecursoUseCase;
    private final MapperUtils mapperUtils;

    public RegresarRecursoUseCase(RecursoRepository recursoRepository, MapperUtils mapperUtils) {
        this.recursoRepository = recursoRepository;
        this.mapperUtils = mapperUtils;
        this.modificarRecursoUseCase = new ModificarRecursoUseCase(recursoRepository, mapperUtils);
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id no puede estar nulo");
        return recursoRepository.findById(id).flatMap(
          recurso -> {
              if (recurso.getCantidadPrestada() > 0){
                  recurso.setCantidadPrestada(recurso.getCantidadPrestada() - 1);
                  return  modificarRecursoUseCase.apply(mapperUtils.mapEntidadToRecurso().apply(recurso))
                          .thenReturn("El recurso con el nombre " + recurso.getNombre()+ "ha sido devuelto");
              }
              return Mono.just("El recurso " +recurso.getNombre() + "NO figura en la base como prestado por el momento");
          }
        );
    }
}
