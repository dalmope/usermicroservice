package co.dalmope.usermicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Especialidad {
    private Long id;
    private String nombre;
    private String descripcion;
    private Estado estado;
}
