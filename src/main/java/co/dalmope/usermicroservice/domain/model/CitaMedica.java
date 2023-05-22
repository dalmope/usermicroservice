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
    private Date fechaInicio;
    private String motivo;
    private EstadoCita estado;
    private Long idPaciente;
    private Long idMedico;
    private Long idEspecialidad;
    private Long idConsultorio;
}
