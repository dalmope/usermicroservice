package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    void saveUser(User user);
    void deleteUser(User user);
    List<User> getAllProviders(int page);
    User getProvider(Long id);
    User getEmployee(Long id);
    User getClient(Long id);
}
