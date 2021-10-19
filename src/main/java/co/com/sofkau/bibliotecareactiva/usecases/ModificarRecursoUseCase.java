package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import co.com.sofkau.bibliotecareactiva.usecases.interfacesusecase.GuardarRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public  class ModificarRecursoUseCase implements GuardarRecurso {
    private final RecursoRepository recursoRepository;
    private final MapperUtils mapperUtils;

    public ModificarRecursoUseCase(RecursoRepository recursoRepository, MapperUtils mapperUtils) {
        this.recursoRepository = recursoRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "El id del recurso es requerido");
        return recursoRepository.save(mapperUtils.mapperToRecurso().apply(recursoDTO))
                .map(recurso -> mapperUtils.mapEntidadToRecurso().apply(recurso));
    }
}
