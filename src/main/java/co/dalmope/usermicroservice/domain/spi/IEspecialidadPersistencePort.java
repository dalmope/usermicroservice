package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.Especialidad;

import java.util.List;

public interface IEspecialidadPersistencePort {
    void saveEspecialidad(Especialidad especialidad);
    List<Especialidad> getAllEspecialidades();
    boolean existsAndIsActive(Long id);
    Especialidad getEspecialidad(Long id);
    List<Especialidad> getEspecialidades();
    List<Especialidad> getEspecialidadesActivas();
}
