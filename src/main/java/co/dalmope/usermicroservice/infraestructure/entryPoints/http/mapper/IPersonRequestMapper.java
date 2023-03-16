package co.dalmope.usermicroservice.infraestructure.entryPoints.http.mapper;

import co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.request.PersonRequest;
import co.dalmope.usermicroservice.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonRequestMapper {
    Person toPerson(PersonRequest personRequest);
}
