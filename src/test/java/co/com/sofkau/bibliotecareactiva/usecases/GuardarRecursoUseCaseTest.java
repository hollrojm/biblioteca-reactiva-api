package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.collections.Recurso;
import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Objects;


class GuardarRecursoUseCaseTest {

    @SpyBean
    private GuardarRecursoUseCase guardarRecursoUseCase;

    @MockBean
    private RecursoRepository recursoRepository;

    @Test
    void guardarRecurso(){

        var recursoDTO = new RecursoDTO("111-222","Historia de dos ciudades",LocalDate.parse("2020-10-19"), 10,
                0,"Libro electrónico","Crítica social" );

        var recurso = new Recurso();
        recurso.setId("111-222");
        recurso.setNombre("Historia de dos ciudades");
        recurso.setFecha(LocalDate.parse("2020-10-19"));
        recurso.setCantidadDisponible(10);
        recurso.setCantidadPrestada(0);
        recurso.setTipo("Libro electrónico");
        recurso.setTematica("Crítica social");

        Mockito.when(recursoRepository.save(Mockito.any(Recurso.class))).thenReturn(Mono.just(recurso));

        var result = guardarRecursoUseCase.apply(recursoDTO);

        Assertions.assertEquals(Objects.requireNonNull(result.block()).getId(),"111-222");
        Assertions.assertEquals(Objects.requireNonNull(result.block()).getNombre(),"Historia de dos ciudades");
        Assertions.assertEquals(Objects.requireNonNull(result.block()).getFecha(),LocalDate.parse("2020-10-19"));
        Assertions.assertEquals(Objects.requireNonNull(result.block()).getCantidadDisponible(),10);
        Assertions.assertEquals(Objects.requireNonNull(result.block()).getCantidadPrestada(),0);
        Assertions.assertEquals(Objects.requireNonNull(result.block()).getTipo(),"Libro electrónico");
        Assertions.assertEquals(Objects.requireNonNull(result.block()).getTematica(),"Crítica social");

        }
    }