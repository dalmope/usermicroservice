package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.Person;

public interface IPersonPersistencePort {
    void savePerson(Person person);
    Person getPerson(Long id);
    boolean existsById(Long id);
}
