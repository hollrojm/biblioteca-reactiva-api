package co.com.sofkau.bibliotecareactiva.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document
public final class Recurso {

        @Id
        private String id;
        private String nombre;
        private LocalDate fecha;
        private Integer cantidadDisponible;
        private Integer cantidadPrestada;
        private String tipo;
        private String tematica;
}
