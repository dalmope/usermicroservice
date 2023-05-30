package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.CitaMedica;

import java.util.List;

public interface ICitaMedicaPersistencePort {
    void saveCitaMedica(CitaMedica citaMedica);
    List<CitaMedica> getAll();
    List<CitaMedica> getAllByPaciente(Long id);
    CitaMedica getById(Long id);
}
