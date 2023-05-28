package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.IRoleServicePort;
import co.dalmope.usermicroservice.domain.model.Role;
import co.dalmope.usermicroservice.domain.spi.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public List<Role> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }

    @Override
    public List<Role> getAllEspecialidades() {
        List<Role> roles = rolePersistencePort.getAllRoles();
        roles.removeIf(role -> role.getId() <= 10);
        return roles;
    }

    @Override
    public void create(Role role) {
        if (role.getId() != null) {
            throw new IllegalArgumentException("Role id must be null");
        }
        rolePersistencePort.saveRole(role);
    }

    @Override
    public void update(Role role) {
        if (role.getId() == null) {
            throw new IllegalArgumentException("Role id must not be null");
        }
        rolePersistencePort.saveRole(role);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Role id must not be null");
        }
        if (id <= 10) {
            throw new IllegalArgumentException("Role id must be greater than 10");
        }
        rolePersistencePort.deleteRole(id);
    }
}
