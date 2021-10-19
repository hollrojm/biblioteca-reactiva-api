package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.mapper.MapperUtils;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public  class RegresarRecursoUseCase implements Function<String, Mono<String>> {

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
