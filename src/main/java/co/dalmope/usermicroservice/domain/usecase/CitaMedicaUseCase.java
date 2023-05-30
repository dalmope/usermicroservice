package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.ICitaMedicaServicePort;
import co.dalmope.usermicroservice.domain.exceptions.EspecialidadNotFoundException;
import co.dalmope.usermicroservice.domain.exceptions.PersonNotFoundException;
import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.domain.model.EstadoCita;
import co.dalmope.usermicroservice.domain.spi.ICitaMedicaPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IConsultorioPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IRolePersistencePort;

import java.util.List;

public class CitaMedicaUseCase implements ICitaMedicaServicePort {
    private final ICitaMedicaPersistencePort citaMedicaRepository;
    private final IRolePersistencePort especialidadRepository;
    private final IConsultorioPersistencePort consultorioRepository;
    private final IPersonPersistencePort personRepository;

    public CitaMedicaUseCase(ICitaMedicaPersistencePort citaMedicaRepository, IRolePersistencePort especialidadRepository, IConsultorioPersistencePort consultorioRepository, IPersonPersistencePort personRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
        this.especialidadRepository = especialidadRepository;
        this.consultorioRepository = consultorioRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(CitaMedica citaMedica) {
        if (!especialidadRepository.existsByIdAndEstado(citaMedica.getEspecialidad().getId(), Estado.ACTIVO) ){
            throw new EspecialidadNotFoundException();
        }
        if (personRepository.existsById(citaMedica.getPaciente().getId())){
            throw new PersonNotFoundException();
        }
        citaMedica.setEstado(EstadoCita.POR_ASIGNAR);
        citaMedicaRepository.saveCitaMedica(citaMedica);
    }

    @Override
    public List<CitaMedica> getAll() {
        return citaMedicaRepository.getAll();
    }

    @Override
    public List<CitaMedica> getAllByPaciente(Long id) {
        return citaMedicaRepository.getAllByPaciente(id);
    }

    @Override
    public void asignarCitaMedica(CitaMedica citaMedica) {
        if (!especialidadRepository.existsById(citaMedica.getEspecialidad().getId()) ){
            throw new EspecialidadNotFoundException();
        }
        if (!consultorioRepository.existsAndIsActive(citaMedica.getConsultorio().getId()) ){
            throw new EspecialidadNotFoundException();
        }
        if (personRepository.existsById(citaMedica.getMedico().getId())){
            throw new PersonNotFoundException();
        }
        CitaMedica citaMedicaExistente = citaMedicaRepository.getById(citaMedica.getId());
        citaMedicaExistente.setEstado(EstadoCita.ASIGNADA);
        citaMedicaExistente.setConsultorio(citaMedica.getConsultorio());
        citaMedicaExistente.setMedico(citaMedica.getMedico());
        citaMedicaExistente.setFechaHora(citaMedica.getFechaHora());
        citaMedicaRepository.saveCitaMedica(citaMedica);
    }

}
