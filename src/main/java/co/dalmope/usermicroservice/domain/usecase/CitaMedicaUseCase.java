package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.ICitaMedicaServicePort;
import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.domain.spi.ICitaMedicaPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IConsultorioPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IEspecialidadPersistencePort;

public class CitaMedicaUseCase implements ICitaMedicaServicePort {
    private final ICitaMedicaPersistencePort citaMedicaRepository;
    private final IEspecialidadPersistencePort especialidadRepository;
    private final IConsultorioPersistencePort consultorioRepository;
    private final IPersonServicePort personRepository;

    public CitaMedicaUseCase(ICitaMedicaPersistencePort citaMedicaRepository, IEspecialidadPersistencePort especialidadRepository, IConsultorioPersistencePort consultorioRepository, IPersonServicePort personRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
        this.especialidadRepository = especialidadRepository;
        this.consultorioRepository = consultorioRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(CitaMedica citaMedica) {
        if (!personRepository.existsPerson(citaMedica.getIdMedico())){
            throw new RuntimeException("Medico no existe");
        }
        if (!personRepository.existsPerson(citaMedica.getIdPaciente())){
            throw new RuntimeException("Paciente no existe");
        }
        if (!especialidadRepository.existsAndIsActive(citaMedica.getIdEspecialidad()) ){
            throw new RuntimeException("Especialidad no existe");
        }
        if (!consultorioRepository.existsAndIsActive(citaMedica.getIdConsultorio())){
            throw new RuntimeException("Consultorio no existe");
        }

        citaMedicaRepository.saveCitaMedica(citaMedica);
    }

}
