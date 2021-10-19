package co.com.sofkau.bibliotecareactiva.routers;

import co.com.sofkau.bibliotecareactiva.model.RecursoDTO;
import co.com.sofkau.bibliotecareactiva.usecases.*;
import co.com.sofkau.bibliotecareactiva.usecases.interfacesusecase.RecomendarPorTipoyTematica;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public  class RecursoRouters {
    @Bean
    public RouterFunction<ServerResponse> getAll(ListarRecursoUseCase listarRecursoUseCase) {
        return route(GET("/recursos"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listarRecursoUseCase.get(), RecursoDTO.class)
                        )
        );
    }



    @Bean
    public RouterFunction<ServerResponse> update(ModificarRecursoUseCase modificarRecursoUseCase) {
        Function<RecursoDTO, Mono<ServerResponse>> executor = recursoDTO -> modificarRecursoUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/recursos/editar")
                        .and(accept(MediaType.APPLICATION_JSON)), request -> request
                        .bodyToMono(RecursoDTO.class)
                        .flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> delete(EliminaRecursoUseCase eliminarRecursoUseCase) {
        return route(
                DELETE("/recursos/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(eliminarRecursoUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> disponibilidad(VerificarDisponibilidadUseCase verificarDisponibilidadUseCase) {
        return route(
                GET("/recursos/disponibilidad/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(verificarDisponibilidadUseCase.apply(request.pathVariable("id")), String.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())

        );
    }

    @Bean
    public RouterFunction<ServerResponse> prestarRecurso(PrestarUseCase prestarUseCase) {
        return route(
                PUT("/recursos/prestar/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(prestarUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> recomedarPorTematica(RecomendarPorTematicaUseCase recomendarPorTematicaUseCase) {
        return route(
                GET("/recursos/recomendarportematica/{tematica}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTematicaUseCase.get(request.pathVariable("tema")), RecursoDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> recomendarPorTipo(RecomendarPorTipoUseCase recomendarPorTipoUseCase) {
        return route(
                GET("/recursos/recomendarportipo/{tipo}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTipoUseCase.get(request.pathVariable("tipo")), RecursoDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
    @Bean
    public RouterFunction<ServerResponse> recomendarPorTipoyTematica(RecomendarPorTipoyTematica recomendarPorTipoyTematica) {
        return route(
                GET("/recursos/recomendarportipoytematica/{tipo}/{tematica}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTipoyTematica.get(request.pathVariable("tipo"), request.pathVariable("tematica")), RecursoDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
    @Bean
    public RouterFunction<ServerResponse> regresarRecurso(RegresarRecursoUseCase regresarRecursoUseCase) {
        return route(
                PUT("/recursos/regresar/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(regresarRecursoUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }
}
