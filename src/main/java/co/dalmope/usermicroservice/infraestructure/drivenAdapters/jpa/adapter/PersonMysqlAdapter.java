package co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.model.Person;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.entity.PersonEntity;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.entity.UserEntity;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.exception.MailAlreadyExistsException;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.exception.PersonAlreadyExistsException;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.mapper.IPersonEntityMapper;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.repository.IPersonRepository;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.repository.IRoleRepository;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.repository.IUserRepository;
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
}
