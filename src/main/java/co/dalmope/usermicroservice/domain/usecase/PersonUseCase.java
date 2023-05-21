package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import co.dalmope.usermicroservice.domain.model.Person;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;

public class PersonUseCase implements IPersonServicePort {
    private final IPersonPersistencePort personPersistencePort;

    public PersonUseCase(IPersonPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public void savePerson(Person person) {
        personPersistencePort.savePerson(person);
    }

    @Override
    public Person getPerson(Long id) {
        return personPersistencePort.getPerson(id);
    }
}
