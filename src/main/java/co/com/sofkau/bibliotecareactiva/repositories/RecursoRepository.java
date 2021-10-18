package co.com.sofkau.bibliotecareactiva.repositories;

import co.com.sofkau.bibliotecareactiva.collections.Recurso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RecursoRepository extends ReactiveCrudRepository<Recurso, String> {
    Flux<Recurso> encontrarPorTipo(final String tipo);
    Flux<Recurso> encontrarPorTematica(final String tematica);
    Flux<Recurso> encontrarPorTipoyTematica (final String tipo, final String tematica);
}
