package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.domain.model.EstadoCita;

import java.util.List;

public interface ICitaMedicaPersistencePort {
    void saveCitaMedica(CitaMedica citaMedica);
    List<CitaMedica> getAll();
    List<CitaMedica> getAllByEstado(EstadoCita estado);
    List<CitaMedica> getAllByPacienteId(Long id);
    CitaMedica getById(Long id);
}
