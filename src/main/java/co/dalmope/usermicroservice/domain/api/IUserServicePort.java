package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    void saveUser(User user);
    void deleteUser(User user);
    List<User> getAllMeds();
    User getMed(Long id);
    User getEmployee(Long id);
    User getClient(Long id);
}
