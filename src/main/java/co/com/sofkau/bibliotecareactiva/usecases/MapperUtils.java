package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.collections.Recurso;
import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public final class MapperUtils {
    public Function<RecursoDTO, Recurso> mapperToRecurso() {
        return updateRecurso -> {
            var recurso = new Recurso();
            recurso.setId(updateRecurso.getId());
            recurso.setNombre(updateRecurso.getNombre());
            recurso.setFecha(updateRecurso.getFecha());
            recurso.setCantidadDisponible(updateRecurso.getCantidadDisponible());
            recurso.setCantidadPrestada(updateRecurso.getCantidadPrestada());
            recurso.setTipo(updateRecurso.getTipo());
            recurso.setTematica(updateRecurso.getTematica());
            return recurso;
        };
    }
    public Function<Recurso, RecursoDTO> mapEntidadToRecurso() {
        return entity -> new RecursoDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getFecha(),
                entity.getCantidadDisponible(),
                entity.getCantidadPrestada(),
                entity.getTipo(),
                entity.getTematica()
        );
    }
}
