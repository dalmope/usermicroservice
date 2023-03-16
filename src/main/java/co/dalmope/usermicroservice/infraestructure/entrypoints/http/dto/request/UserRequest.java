package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequest {
    private Long idPerson;
    private Long idRole;
}
