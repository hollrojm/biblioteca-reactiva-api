package co.com.sofkau.bibliotecareactiva.routers;

import co.com.sofkau.bibliotecareactiva.collections.Recurso;
import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.repositories.RecursoRepository;
import co.com.sofkau.bibliotecareactiva.usecases.GuardarRecursoUseCase;
import co.com.sofkau.bibliotecareactiva.mapper.MapperUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;



@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearRecursoRouter.class, GuardarRecursoUseCase.class, MapperUtils.class})
class CrearRecursoRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testRouterCrearRecurso() {

        Recurso recurso = new Recurso();
        recurso.setId("111-222");
        recurso.setNombre("Historia de dos ciudades");
        recurso.setFecha(LocalDate.parse("2020-10-19"));
        recurso.setCantidadDisponible(10);
        recurso.setCantidadPrestada(0);
        recurso.setTipo("Libro electrónico");
        recurso.setTematica("Crítica social");


        RecursoDTO recursoDTO = new RecursoDTO(recurso.getId(),
                recurso.getNombre(),recurso.getFecha(),recurso.getCantidadDisponible(),recurso.getCantidadPrestada(),
                recurso.getTipo(),recurso.getTematica());

        Mono<Recurso> recursoMono = Mono.just(recurso);
        when(recursoRepository.save(any())).thenReturn(recursoMono);

        webTestClient.post()
                .uri("/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk();
    }

}