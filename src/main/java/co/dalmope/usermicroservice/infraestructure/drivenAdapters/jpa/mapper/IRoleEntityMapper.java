package co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.mapper;

import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.entity.RoleEntity;
import co.dalmope.usermicroservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {
    List<Role> toRoleList(List<RoleEntity> roleEntityList);
}
