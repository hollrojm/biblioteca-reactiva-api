package co.com.sofkau.bibliotecareactiva.routers;

import co.com.sofkau.bibliotecareactiva.mapper.MapperUtils;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import co.com.sofkau.bibliotecareactiva.usecases.EliminaRecursoUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarRecursoRoute.class, EliminaRecursoUseCase.class, MapperUtils.class})
class EliminarRecursoRouteTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRouterEliminarRecurso(){
        Mono<Void> voidReturn = Mono.empty();

        Mockito.when(recursoRepository.deleteById("1234")).thenReturn(voidReturn);
        webTestClient.delete().uri("/recursos/eliminar/{id}", "1234")
                .exchange()
                .expectStatus()
                .isAccepted();
    }

}