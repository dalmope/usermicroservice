package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.Person;

public interface IPersonServicePort {
    void savePerson(Person person);
    Person getPerson(Long id);
}
