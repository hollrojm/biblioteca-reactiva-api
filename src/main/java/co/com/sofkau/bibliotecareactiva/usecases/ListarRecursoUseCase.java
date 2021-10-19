package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

public class ListarRecursoUseCase implements Supplier<Flux<RecursoDTO>> {

    private final RecursoRepository recursoRepository;
    private final MapperUtils mapperUtils;

    public ListarRecursoUseCase(RecursoRepository recursoRepository, MapperUtils mapperUtils) {
        this.recursoRepository = recursoRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return recursoRepository.findAll().map(mapperUtils.mapEntidadToRecurso());
    }
}
