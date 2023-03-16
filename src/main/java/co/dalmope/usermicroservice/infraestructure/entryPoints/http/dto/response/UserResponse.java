package co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private Long idPerson;
    private Long idRole;
}
