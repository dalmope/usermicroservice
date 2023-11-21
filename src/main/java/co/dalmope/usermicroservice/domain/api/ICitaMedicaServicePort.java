package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.domain.model.EstadoCita;

import java.util.List;

public interface ICitaMedicaServicePort {
    void create(CitaMedica citaMedica);
    List<CitaMedica> getAll();
    List<CitaMedica> getAllByCodigo(String id);
    List<CitaMedica> getAllByEstado(EstadoCita estado);
    void asignarCitaMedica(CitaMedica citaMedica);
    void delete(Long id);
}
