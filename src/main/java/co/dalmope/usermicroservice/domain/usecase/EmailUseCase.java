package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.application.security.jwt.JwtProvider;
import co.dalmope.usermicroservice.domain.api.IEmailServicePort;
import co.dalmope.usermicroservice.domain.model.Person;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;
import co.dalmope.usermicroservice.domain.spi.ISendEmailPort;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.RecoveryPasswordRequest;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.UUID;

public class EmailUseCase implements IEmailServicePort {

    @Value("${mail.url-frontend}")
    private String urlFrontend;

    private final ISendEmailPort sendEmailPort;
    private final IPersonPersistencePort personPersistencePort;
    private final JwtProvider jwtProvider;


    public EmailUseCase(ISendEmailPort sendEmailPort, IPersonPersistencePort personPersistencePort, JwtProvider jwtProvider) {
        this.sendEmailPort = sendEmailPort;
        this.personPersistencePort = personPersistencePort;
        this.jwtProvider = jwtProvider;
    }

    public void sendEmailRecoveryPassword(RecoveryPasswordRequest recoveryPasswordRequest) {
        Person person = personPersistencePort.getPersonByDniNumber(recoveryPasswordRequest.getDniNumber());
        person.setTokenPassword(jwtProvider.generateToken(person.getName() + UUID.randomUUID()));
        Map<String,String> values = Map.of("reset_password_link",urlFrontend + person.getTokenPassword());
        sendEmailPort.sendEmail(person.getMail(), "Gescli: Recuperación de contraseña", "recover-password", values);
        personPersistencePort.updatePerson(person);
    }

}
