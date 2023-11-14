package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.RoleEntity;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.NoDataFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.RoleNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IRoleRepository;
import co.dalmope.usermicroservice.domain.model.Role;
import co.dalmope.usermicroservice.domain.spi.IRolePersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IRoleEntityMapper;
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

    @Override
    public void saveRole(Role role) {
        role.setEstado(Estado.ACTIVO);
        roleRepository.save(roleEntityMapper.toRoleEntity(role));
    }

    @Override
    public void deleteRole(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);
        roleEntity.setEstado(Estado.INACTIVO);
        roleRepository.save(roleEntity);
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public Role getRoleByIdAndEstado(Long id, Estado estado) {
        return roleEntityMapper.toRole(roleRepository.findByIdAndEstado(id, estado)
                .orElseThrow(RoleNotFoundException::new));
    }

    @Override
    public List<Role> getAllRolesByEstado(Estado estado) {
        return roleEntityMapper.toRoleList(roleRepository.findAllByEstado(Estado.ACTIVO));
    }
}
