package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.collections.Recurso;
import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public  class MapperUtils {
    public Function<RecursoDTO, Recurso> mapperToRecurso() {
        return modificarRecurso -> {
            var recurso = new Recurso();
            recurso.setId(modificarRecurso.getId());
            recurso.setNombre(modificarRecurso.getNombre());
            recurso.setFecha(modificarRecurso.getFecha());
            recurso.setCantidadDisponible(modificarRecurso.getCantidadDisponible());
            recurso.setCantidadPrestada(modificarRecurso.getCantidadPrestada());
            recurso.setTipo(modificarRecurso.getTipo());
            recurso.setTematica(modificarRecurso.getTematica());
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
