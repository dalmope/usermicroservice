package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private Long idPerson;
    private Long idRole;
}
