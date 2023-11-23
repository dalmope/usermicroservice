package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.Person;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.ChangePasswordRequest;

public interface IPersonServicePort {
    void savePerson(Person person);
    void changePassword(ChangePasswordRequest changePasswordRequest);
    Person getPerson(Long id);
    boolean existsPerson(Long id);
}
