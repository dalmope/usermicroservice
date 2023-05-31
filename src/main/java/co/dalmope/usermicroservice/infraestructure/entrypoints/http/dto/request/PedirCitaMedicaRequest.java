package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PedirCitaMedicaRequest {
    private Long idEspecialidad;
    private String codigo;
    private String motivo;
}
