package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AsignarCitaRequest {
    private Long idCita;
    private Long idMedico;
    private Long idConsultorio;
    private Date fechaHora;
}
