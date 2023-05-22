package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper;

import co.dalmope.usermicroservice.domain.model.Especialidad;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.EspecialidadEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEspecialidadMapper {
    Especialidad toDomain(EspecialidadEntity especialidad);
    EspecialidadEntity toEntity(Especialidad especialidad);
    List<Especialidad> toEspecialidadList(List<EspecialidadEntity> especialidadEntityList);
}
