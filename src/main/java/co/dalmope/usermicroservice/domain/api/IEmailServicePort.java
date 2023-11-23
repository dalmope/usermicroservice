package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.RecoveryPasswordRequest;

public interface IEmailServicePort {
    void sendEmailRecoveryPassword(RecoveryPasswordRequest recoveryPasswordRequest);
}
