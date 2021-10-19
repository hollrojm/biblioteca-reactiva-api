package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import co.com.sofkau.bibliotecareactiva.usecases.interfacesusecase.RecomendarPorTipo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public  class RecomendarPorTipoUseCase implements RecomendarPorTipo {

    private final RecursoRepository recursoRepository;
    private final MapperUtils mapperUtils;

    public RecomendarPorTipoUseCase(RecursoRepository recursoRepository, MapperUtils mapperUtils) {
        this.recursoRepository = recursoRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get(String tipo) {
        return recursoRepository.findByTipo(tipo).map(mapperUtils.mapEntidadToRecurso());
    }
}
