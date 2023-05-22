package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper;

import co.dalmope.usermicroservice.domain.model.Consultorio;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.ConsultorioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IConsultorioMapper {
    Consultorio toDomain(ConsultorioEntity consultorio);
    ConsultorioEntity toEntity(Consultorio consultorio);
    List<Consultorio> toConsultorioList(List<ConsultorioEntity> consultorioEntityList);

}
