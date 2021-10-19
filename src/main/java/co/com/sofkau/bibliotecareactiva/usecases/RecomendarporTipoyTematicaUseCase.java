package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.mapper.MapperUtils;
import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import co.com.sofkau.bibliotecareactiva.usecases.interfacesusecase.RecomendarPorTipoyTematica;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@Validated
public  class RecomendarporTipoyTematicaUseCase implements RecomendarPorTipoyTematica {
    private final RecursoRepository recursoRepository;
    private final MapperUtils mapperUtils;

    public RecomendarporTipoyTematicaUseCase(RecursoRepository recursoRepository, MapperUtils mapperUtils) {
        this.recursoRepository = recursoRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<RecursoDTO> get(String tipo, String tematica) {
        Objects.requireNonNull(tipo, "Debe ingresar el tipo del recurso");
        Objects.requireNonNull(tematica,"Debe ingresar la tematica del recurso");
        return recursoRepository.findAllByTipoAndTematica(tipo, tematica)
                .map(mapperUtils.mapEntidadToRecurso())
                .distinct();
    }
}
