package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.application.security.jwt.JwtProvider;
import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import co.dalmope.usermicroservice.domain.exceptions.InvalidPasswordException;
import co.dalmope.usermicroservice.domain.exceptions.InvalidTokenException;
import co.dalmope.usermicroservice.domain.model.Person;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.ChangePasswordRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PersonUseCase implements IPersonServicePort {
    private final IPersonPersistencePort personPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public PersonUseCase(IPersonPersistencePort personPersistencePort, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.personPersistencePort = personPersistencePort;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void savePerson(Person person) {
        personPersistencePort.savePerson(person);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Person person = personPersistencePort.getPersonByTokenPassword(request.getTokenPassword());
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new InvalidPasswordException();
        }

        if (!jwtProvider.validateToken(request.getTokenPassword())) {
            throw new InvalidTokenException();
        }

        person.setPassword(passwordEncoder.encode(request.getPassword()));
        person.setTokenPassword(null);
        personPersistencePort.updatePerson(person);
    }

    @Override
    public Person getPerson(Long id) {
        return personPersistencePort.getPerson(id);
    }

    @Override
    public boolean existsPerson(Long id) {
        return personPersistencePort.getPerson(id) != null;
    }
}
