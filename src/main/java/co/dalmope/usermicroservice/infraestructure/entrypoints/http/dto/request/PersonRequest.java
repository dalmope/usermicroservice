package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonRequest {
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String address;
    private String idDniType;
    private String dniNumber;
    private String password;
}
