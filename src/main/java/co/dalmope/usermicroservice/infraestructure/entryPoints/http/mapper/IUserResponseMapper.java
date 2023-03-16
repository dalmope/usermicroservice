package co.dalmope.usermicroservice.infraestructure.entryPoints.http.mapper;

import co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.response.UserResponse;
import co.dalmope.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    @Mapping(target = "idPerson", source = "person.id")
    @Mapping(target = "idRole", source = "role.id")
    UserResponse toResponse(User user);
    List<UserResponse> toResponseList(List<User> userList);
}
