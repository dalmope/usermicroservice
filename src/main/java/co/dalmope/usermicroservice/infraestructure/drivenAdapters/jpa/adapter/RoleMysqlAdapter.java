package co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.adapter;

import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.entity.RoleEntity;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.exception.NoDataFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.repository.IRoleRepository;
import co.dalmope.usermicroservice.domain.model.Role;
import co.dalmope.usermicroservice.domain.spi.IRolePersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.mapper.IRoleEntityMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleMysqlAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    @Override
    public List<Role> getAllRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        if (roleEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return roleEntityMapper.toRoleList(roleEntityList);
    }
}
