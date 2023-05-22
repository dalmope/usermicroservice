package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.model.Consultorio;
import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.domain.spi.IConsultorioPersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.UserNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IConsultorioMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IConsultorioRepository;

import java.util.List;

public class ConsultorioAdapter implements IConsultorioPersistencePort {

    private final IConsultorioRepository consultorioRepository;
    private final IConsultorioMapper consultorioMapper;

    public ConsultorioAdapter(IConsultorioRepository consultorioRepository, IConsultorioMapper consultorioMapper) {
        this.consultorioRepository = consultorioRepository;
        this.consultorioMapper = consultorioMapper;
    }

    @Override
    public void saveConsultorio(Consultorio consultorio) {
        consultorioRepository.save(consultorioMapper.toEntity(consultorio));
    }

    @Override
    public List<Consultorio> getAllConsultorios() {
        return consultorioMapper.toConsultorioList(consultorioRepository.findAll());
    }

    @Override
    public boolean existsConsultorio(Long id) {
        return consultorioRepository.existsById(id);
    }

    @Override
    public boolean existsAndIsActive(Long id) {
        return consultorioRepository.existsByIdAndEstado(id, Estado.ACTIVO);
    }

    @Override
    public Consultorio getConsultorio(Long id) {
        return consultorioMapper.toDomain(consultorioRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
