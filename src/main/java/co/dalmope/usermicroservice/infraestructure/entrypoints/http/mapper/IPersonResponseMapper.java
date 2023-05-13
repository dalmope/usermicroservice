package co.dalmope.usermicroservice.infraestructure.entrypoints.http.mapper;

import co.dalmope.usermicroservice.domain.model.User;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonResponseMapper {
    @Mapping(source = "person.name", target = "name")
    @Mapping(source = "person.surname", target = "surname")
    @Mapping(source = "person.mail", target = "mail")
    @Mapping(source = "person.phone", target = "phone")
    @Mapping(source = "person.address", target = "address")
    @Mapping(source = "person.idDniType", target = "idDniType")
    @Mapping(source = "person.dniNumber", target = "dniNumber")
    PersonResponse userToPersonResponse(User user);
    List<PersonResponse> userListToPersonResponseList(List<User> userList);
}
