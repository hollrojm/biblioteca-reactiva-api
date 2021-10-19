package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import co.com.sofkau.bibliotecareactiva.usecases.interfacesusecase.RecomendarPorTematica;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public  class RecomendarPorTematicaUseCase implements RecomendarPorTematica {

    private final RecursoRepository recursoRepository;
    private final MapperUtils mapperUtils;

    public RecomendarPorTematicaUseCase(RecursoRepository recursoRepository, MapperUtils mapperUtils) {
        this.recursoRepository = recursoRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<RecursoDTO> get(String tematica) {
        return recursoRepository.findByTematica(tematica).map(mapperUtils.mapEntidadToRecurso());
    }
}
