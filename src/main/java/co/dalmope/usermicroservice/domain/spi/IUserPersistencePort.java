package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUser(User user);
    void deleteUser(User user);
    List<User> getAllMeds();
    User getMed(Long id);
    User getUser(Long id);
    User getAdmin(Long id);
}
