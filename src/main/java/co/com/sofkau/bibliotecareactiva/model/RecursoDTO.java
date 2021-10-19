package co.com.sofkau.bibliotecareactiva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class RecursoDTO {

    private String id;
    //@NotBlank(message = "El nombre no debe estar vacio")
    private String nombre;
    private LocalDate fecha;
    //@NotNull(message = "Debe ingresar una cantidad")
    private Integer cantidadDisponible;
    private Integer cantidadPrestada;
    //@NotBlank(message = "Ingrese un tipo de recurso")
    private String  tipo;
    //@NotBlank(message = "Ingrese una una tematica del recurso")
    private String  tematica;

}
