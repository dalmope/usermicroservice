package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {
    List<Role> getAllRoles();

    List<Role> getAllEspecialidades();
}
