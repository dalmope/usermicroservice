package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.Especialidad;

import java.util.List;

public interface IEspecialidadServicePort {
    void create(Especialidad especialidad);
    void update(Especialidad especialidad);
    void desactive(Long id);
    List<Especialidad> getAll();
    List<Especialidad> getAllActivos();
}
