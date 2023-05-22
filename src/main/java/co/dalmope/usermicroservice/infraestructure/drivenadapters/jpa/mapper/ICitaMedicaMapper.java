package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper;

import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.CitaMedicaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICitaMedicaMapper {
    CitaMedica toDomain(CitaMedicaEntity citaMedicaEntity);
    CitaMedicaEntity toEntity(CitaMedica citaMedica);
    List<CitaMedica> toCitaMedicaList(List<CitaMedicaEntity> citaMedicaEntityList);
}
