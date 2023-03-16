package co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequest {
    private Long idPerson;
    private Long idRole;
}
