package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.exceptions.PersonNotFoundException;
import co.dalmope.usermicroservice.domain.model.User;
import co.dalmope.usermicroservice.domain.spi.IUserPersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.UserEntity;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.NoDataFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.RoleNotAllowedForCreationException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.RoleNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.UserAlreadyExistsException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.UserNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IUserEntityMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IPersonRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IRoleRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static co.dalmope.usermicroservice.application.Constants.ADMIN_ROLE_ID;
import static co.dalmope.usermicroservice.application.Constants.MED_ROLE_ID;
import static co.dalmope.usermicroservice.application.Constants.USER_ROLE_ID;

@RequiredArgsConstructor
@Transactional
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IPersonRepository personRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public void saveUser(User user) {
        if (user.getRole().getId().equals(ADMIN_ROLE_ID)) {
            throw new RoleNotAllowedForCreationException();
        }
        if (userRepository.findByPersonEntityIdAndRoleEntityId(user.getPerson().getId(), user.getRole().getId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (!personRepository.existsById(user.getPerson().getId())) {
            throw new PersonNotFoundException();
        }
        if (!roleRepository.existsById(user.getRole().getId())) {
            throw new RoleNotFoundException();
        }

        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUser(User user) {
        if (userRepository.findByPersonEntityIdAndRoleEntityId(user.getPerson().getId(), user.getRole().getId()).isPresent()) {
            userRepository.deleteByPersonEntityIdAndRoleEntityId(user.getPerson().getId(), user.getRole().getId());
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> getAllMeds() {
        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(MED_ROLE_ID);
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getMed(Long id) {
        UserEntity userEntity = userRepository.findByPersonEntityIdAndRoleEntityId(id, MED_ROLE_ID).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getUser(Long id) {
        UserEntity userEntity = userRepository.findByPersonEntityIdAndRoleEntityId(id, USER_ROLE_ID).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getAdmin(Long id) {
        UserEntity userEntity = userRepository.findByPersonEntityIdAndRoleEntityId(id, ADMIN_ROLE_ID).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }
}
