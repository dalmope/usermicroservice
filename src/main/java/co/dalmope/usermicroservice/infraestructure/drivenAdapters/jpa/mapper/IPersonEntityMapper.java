package co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.mapper;

import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.entity.PersonEntity;
import co.dalmope.usermicroservice.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonEntityMapper {
    PersonEntity toEntity(Person person);
}
