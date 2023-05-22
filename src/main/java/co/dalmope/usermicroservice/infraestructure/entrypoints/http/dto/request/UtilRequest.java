package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class UtilRequest {
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
}
