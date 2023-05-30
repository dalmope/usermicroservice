package co.dalmope.usermicroservice.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CitaMedica {
    private Long id;
    private Date fechaHora;
    private String motivo;
    private EstadoCita estado;
    private Role especialidad;
    private Consultorio consultorio;
    private Person paciente;
    private Person medico;
}
