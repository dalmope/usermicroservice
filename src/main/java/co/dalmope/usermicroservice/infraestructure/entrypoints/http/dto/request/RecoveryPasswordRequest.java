package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request;

import lombok.Getter;

@Getter
public class RecoveryPasswordRequest {
    private String dniNumber;
}
