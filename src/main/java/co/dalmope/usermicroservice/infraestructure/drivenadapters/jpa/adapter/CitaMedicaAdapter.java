package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.domain.spi.ICitaMedicaPersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.NoDataFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.ICitaMedicaMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.ICitaMedicaRepository;

import java.util.List;

public class CitaMedicaAdapter implements ICitaMedicaPersistencePort {

    private final ICitaMedicaRepository citaMedicaRepository;
    private final ICitaMedicaMapper citaMedicaMapper;

    public CitaMedicaAdapter(ICitaMedicaRepository citaMedicaRepository, ICitaMedicaMapper citaMedicaMapper) {
        this.citaMedicaRepository = citaMedicaRepository;
        this.citaMedicaMapper = citaMedicaMapper;
    }

    @Override
    public void saveCitaMedica(CitaMedica citaMedica) {
        citaMedicaRepository.save(citaMedicaMapper.toEntity(citaMedica));
    }
    @Override
    public List<CitaMedica> getAll() {
        return citaMedicaMapper.toDomainList(citaMedicaRepository.findAll());
    }

    @Override
    public List<CitaMedica> getAllByPacienteId(Long id) {
        return citaMedicaMapper.toDomainList(citaMedicaRepository.findAllByPacienteId(id));
    }
    @Override
    public CitaMedica getById(Long id) {
        return citaMedicaMapper.toDomain(citaMedicaRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }
}
