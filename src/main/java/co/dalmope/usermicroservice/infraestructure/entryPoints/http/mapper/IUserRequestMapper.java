package co.dalmope.usermicroservice.infraestructure.entryPoints.http.mapper;

import co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.request.UserRequest;
import co.dalmope.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    @Mapping(target = "person.id", source = "idPerson")
    @Mapping(target = "role.id", source = "idRole")
    User toUser(UserRequest userRequest);
}
