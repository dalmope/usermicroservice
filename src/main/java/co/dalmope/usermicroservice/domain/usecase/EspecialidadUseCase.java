package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.IEspecialidadServicePort;
import co.dalmope.usermicroservice.domain.model.Especialidad;
import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.domain.spi.IEspecialidadPersistencePort;

import java.util.List;

public class EspecialidadUseCase implements IEspecialidadServicePort {

    private final IEspecialidadPersistencePort especialidadRepository;

    public EspecialidadUseCase(IEspecialidadPersistencePort especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public void create(Especialidad especialidad) {
        especialidad.setId(null);
        especialidad.setEstado(Estado.ACTIVO);
        especialidadRepository.saveEspecialidad(especialidad);
    }

    @Override
    public void update(Especialidad especialidad) {
        especialidadRepository.saveEspecialidad(especialidad);
    }

    @Override
    public void desactive(Long id) {
        Especialidad especialidad = especialidadRepository.getEspecialidad(id);
        especialidad.setEstado(Estado.INACTIVO);
        especialidadRepository.saveEspecialidad(especialidad);
    }

    @Override
    public List<Especialidad> getAll() {
        return especialidadRepository.getEspecialidades();
    }

    @Override
    public List<Especialidad> getAllActivos() {
        return especialidadRepository.getEspecialidadesActivas();
    }

}
