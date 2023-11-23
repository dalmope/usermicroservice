package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.Person;

public interface IPersonPersistencePort {
    void savePerson(Person person);
    void updatePerson(Person person);
    Person getPerson(Long id);
    boolean existsById(Long id);
    Person getPersonByDniNumber(String dniNumber);
    Person getPersonByTokenPassword(String tokenPassword);
}
