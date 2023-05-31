package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.IUserServicePort;
import co.dalmope.usermicroservice.domain.model.User;
import co.dalmope.usermicroservice.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userPersistencePort.deleteUser(user);
    }

    @Override
    public List<User> getAllMeds() {
        return userPersistencePort.getAllMeds();
    }

    @Override
    public User getMed(Long id) {
        return userPersistencePort.getMed(id);
    }

    @Override
    public User getEmployee(Long id) {
        return userPersistencePort.getUser(id);
    }

    @Override
    public User getClient(Long id) {
        return userPersistencePort.getAdmin(id);
    }
}
