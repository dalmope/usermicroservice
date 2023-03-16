package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    List<Role> getAllRoles();
}
