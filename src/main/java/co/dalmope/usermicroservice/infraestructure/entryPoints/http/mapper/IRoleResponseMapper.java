package co.dalmope.usermicroservice.infraestructure.entryPoints.http.mapper;

import co.dalmope.usermicroservice.domain.model.Role;
import co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleResponseMapper {
    List<RoleResponse> toResponseList(List<Role> roleList);
}
