package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.model.Especialidad;
import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.domain.spi.IEspecialidadPersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.UserNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IEspecialidadMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IEspecialidadRepository;

import java.util.List;

public class EspecialidadAdapter implements IEspecialidadPersistencePort {

    IEspecialidadRepository especialidadRepository;
    IEspecialidadMapper especialidadMapper;
    @Override
    public void saveEspecialidad(Especialidad especialidad) {
        especialidadRepository.save( especialidadMapper.toEntity(especialidad));
    }

    @Override
    public List<Especialidad> getAllEspecialidades() {
        return especialidadMapper.toEspecialidadList(especialidadRepository.findAll());
    }

    @Override
    public boolean existsAndIsActive(Long id) {
        return especialidadRepository.existsByIdAndEstado(id, Estado.ACTIVO);
    }

    @Override
    public Especialidad getEspecialidad(Long id) {
        return especialidadMapper.toDomain(especialidadRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<Especialidad> getEspecialidades() {
        return especialidadMapper.toEspecialidadList(especialidadRepository.findAll());
    }
}
