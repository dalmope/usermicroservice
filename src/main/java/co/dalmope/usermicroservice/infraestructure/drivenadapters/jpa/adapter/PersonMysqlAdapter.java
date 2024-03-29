package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.model.Person;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.PersonEntity;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.UserEntity;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.MailAlreadyExistsException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.PersonAlreadyExistsException;
import co.dalmope.usermicroservice.domain.exceptions.PersonNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IPersonEntityMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IPersonRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IRoleRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PersonMysqlAdapter implements IPersonPersistencePort {
    private final IPersonRepository personRepository;
    private final IPersonEntityMapper personEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    @Override
    public void savePerson(Person person) {
        if (personRepository.findByDniNumber(person.getDniNumber()).isPresent()) {
            throw new PersonAlreadyExistsException();
        }
        if (personRepository.existsByMail(person.getMail())){
            throw new MailAlreadyExistsException();
        }

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        PersonEntity personEntity = personRepository.saveAndFlush(personEntityMapper.toEntity(person));
        UserEntity userEntity = new UserEntity();
        userEntity.setPersonEntity(personEntity);
        userEntity.setRoleEntity(roleRepository.findByName("ROLE_USER"));
        userRepository.save(userEntity);
    }

    @Override
    public void updatePerson(Person person) {
        if (!personRepository.existsById(person.getId())) {
            throw new PersonNotFoundException();
        }
        personRepository.save(personEntityMapper.toEntity(person));
    }


    @Override
    public Person getPerson(Long id) {
        return personEntityMapper.toDomain(personRepository.findById(id).orElseThrow(PersonNotFoundException::new));
    }

    @Override
    public boolean existsById(Long id) {
        return !personRepository.existsById(id);
    }

    @Override
    public Person getPersonByDniNumber(String dniNumber) {
        return personRepository.findByDniNumber(dniNumber)
                .map(personEntityMapper::toDomain)
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person getPersonByTokenPassword(String tokenPassword) {
        return personRepository.findByTokenPassword(tokenPassword)
                .map(personEntityMapper::toDomain)
                .orElseThrow(PersonNotFoundException::new);
    }
}
