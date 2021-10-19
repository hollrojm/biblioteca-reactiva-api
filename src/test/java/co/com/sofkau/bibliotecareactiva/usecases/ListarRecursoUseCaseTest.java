package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.collections.Recurso;
import co.com.sofkau.bibliotecareactiva.mapper.MapperUtils;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

class ListarRecursoUseCaseTest {

    private RecursoRepository recursoRepository;
    private ListarRecursoUseCase listarRecursoUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapper = new MapperUtils();
        recursoRepository = mock(RecursoRepository.class);
        listarRecursoUseCase = new ListarRecursoUseCase((RecursoRepository) listarRecursoUseCase, mapper);
    }

    @Test
    void listarTodosRecursos(){
        var recurso = new Recurso();
        recurso.setId("111-222");
        recurso.setNombre("Historia de dos ciudades");
        recurso.setFecha(LocalDate.parse("2020-10-19"));
        recurso.setCantidadDisponible(10);
        recurso.setCantidadPrestada(0);
        recurso.setTipo("Libro electrónico");
        recurso.setTematica("Crítica social");
        when(recursoRepository.findAll()).thenReturn(Flux.just(recurso));

        StepVerifier.create(listarRecursoUseCase.get())
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals("111-222");
                    assert questionDTO.getNombre().equals("Historia de dos ciudades");
                    assert questionDTO.getFecha().equals(LocalDate.parse("2021-11-19"));
                    assert questionDTO.getCantidadDisponible().equals(10);
                    assert questionDTO.getCantidadPrestada().equals(0);
                    assert questionDTO.getTipo().equals("Libro electrónico");
                    assert questionDTO.getTematica().equals("Crítica social");


                    return true;
                })
                .verifyComplete();

        verify(recursoRepository).findAll();
    }

}