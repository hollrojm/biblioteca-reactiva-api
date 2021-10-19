package co.com.sofkau.bibliotecareactiva.usecases;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import org.junit.jupiter.api.Assertions;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.lang.management.MemoryNotificationInfo;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EliminaRecursoUseCaseTest {


    @SpyBean
    private EliminaRecursoUseCase eliminaRecursoUseCase;

    @MockBean
    private RecursoRepository recursoRepository;

    @Test
    void eliminarRecurso(){
        var recursoDTO = new RecursoDTO("111-222","Historia de dos ciudades", LocalDate.parse("2020-10-19"), 10,
                0,"Libro electrónico","Crítica social" );

        Mockito.when(recursoRepository.deleteById("111-222")).thenReturn(Mono.empty());

        var result = eliminaRecursoUseCase.apply("111-222").block();
        assertNull(result);
    }

}