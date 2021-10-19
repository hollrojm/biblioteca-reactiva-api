package co.com.sofkau.bibliotecareactiva.repositories;

import co.com.sofkau.bibliotecareactiva.collections.Recurso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RecursoRepository extends ReactiveCrudRepository<Recurso, String> {
    Flux<Recurso> findByTipo(String tipo);
    Flux<Recurso> findByTematica( String tematica);
    Flux<Recurso> findByTipoyTematica(String tipo,  String tematica);
}
