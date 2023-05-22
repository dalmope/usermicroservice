package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.Especialidad;

public interface IEspecialidadServicePort {
    void create(Especialidad especialidad);

    void update(Especialidad especialidad);

    void desactive(Long id);
}
