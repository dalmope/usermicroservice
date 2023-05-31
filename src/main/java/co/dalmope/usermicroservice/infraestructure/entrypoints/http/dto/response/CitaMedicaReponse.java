package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response;

import co.dalmope.usermicroservice.domain.model.Consultorio;
import co.dalmope.usermicroservice.domain.model.EstadoCita;
import co.dalmope.usermicroservice.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class CitaMedicaReponse {
    private Long id;
    private Date fechaHora;
    private String motivo;
    private EstadoCita estado;
    private Role especialidad;
    private Consultorio consultorio;
    private PersonResponse paciente;
    private PersonResponse medico;
}
