package co.dalmope.usermicroservice.infraestructure.entrypoints.http.mapper;

import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.AsignarCitaRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.PedirCitaMedicaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitaMedicaRequestMapper {
    @Mapping(target = "especialidad.id", source = "idEspecialidad")
    @Mapping(target = "paciente.id", source = "idPaciente")
    CitaMedica toUser(PedirCitaMedicaRequest userRequest);

    @Mapping(target = "id", source = "idCita")
    @Mapping(target = "medico.id", source = "idMedico")
    @Mapping(target = "consultorio.id", source = "idConsultorio")
    CitaMedica toCitaMedica(AsignarCitaRequest citaMedicaRequest);
}
