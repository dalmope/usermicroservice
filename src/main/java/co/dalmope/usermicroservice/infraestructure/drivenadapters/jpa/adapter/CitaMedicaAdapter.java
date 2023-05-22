package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.domain.spi.ICitaMedicaPersistencePort;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.ICitaMedicaMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.ICitaMedicaRepository;

public class CitaMedicaAdapter implements ICitaMedicaPersistencePort {

    ICitaMedicaRepository citaMedicaRepository;
    ICitaMedicaMapper citaMedicaMapper;
    @Override
    public void saveCitaMedica(CitaMedica citaMedica) {
        citaMedicaRepository.save(citaMedicaMapper.toEntity(citaMedica));
    }
}
