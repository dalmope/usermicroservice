package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.CitaMedica;

import java.util.List;

public interface ICitaMedicaServicePort {
    void create(CitaMedica citaMedica);
    List<CitaMedica> getAll();
    List<CitaMedica> getAllByCodigo(String id);
    void asignarCitaMedica(CitaMedica citaMedica);
}
