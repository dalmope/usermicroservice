package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    List<Role> getAllRoles();
    void saveRole(Role role);
    void deleteRole(Long id);
    boolean existsById(Long id);
    Role getRoleByIdAndEstado(Long id, Estado estado);
    List<Role> getAllRolesByEstado(Estado estado);
}
