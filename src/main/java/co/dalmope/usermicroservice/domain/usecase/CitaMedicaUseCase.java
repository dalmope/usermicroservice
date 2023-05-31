package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.ICitaMedicaServicePort;
import co.dalmope.usermicroservice.domain.exceptions.DateInvalidException;
import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.domain.model.Consultorio;
import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.domain.model.EstadoCita;
import co.dalmope.usermicroservice.domain.model.Person;
import co.dalmope.usermicroservice.domain.model.Role;
import co.dalmope.usermicroservice.domain.spi.ICitaMedicaPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IConsultorioPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IRolePersistencePort;

import java.sql.Date;
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
        Role especialidad = especialidadRepository.getRoleByIdAndEstado(citaMedica.getEspecialidad().getId(), Estado.ACTIVO);
        Person paciente = personRepository.getPersonByDniNumber(citaMedica.getPaciente().getDniNumber());

        citaMedica.setEspecialidad(especialidad);
        citaMedica.setPaciente(paciente);
        citaMedica.setEstado(EstadoCita.POR_ASIGNAR);

        citaMedicaRepository.saveCitaMedica(citaMedica);
    }

    @Override
    public List<CitaMedica> getAll() {
        return citaMedicaRepository.getAll();
    }

    @Override
    public List<CitaMedica> getAllByCodigo(String id) {
       Person person = personRepository.getPersonByDniNumber(id);
        return citaMedicaRepository.getAllByPacienteId(person.getId());
    }

    @Override
    public void asignarCitaMedica(CitaMedica citaMedica) {
        Date hoy = new Date(System.currentTimeMillis());
        if (citaMedica.getFechaHora().before(hoy)) {
            throw new DateInvalidException();
        }

        Consultorio consultorio = consultorioRepository.getConsultorioByIdAndEstado(citaMedica.getConsultorio().getId(), Estado.ACTIVO);
        Person medico = personRepository.getPerson(citaMedica.getMedico().getId());
        CitaMedica citaMedicaExistente = citaMedicaRepository.getById(citaMedica.getId());

        citaMedicaExistente.setConsultorio(consultorio);
        citaMedicaExistente.setMedico(medico);
        citaMedicaExistente.setEstado(EstadoCita.ASIGNADA);
        citaMedicaExistente.setFechaHora(citaMedica.getFechaHora());

        citaMedicaRepository.saveCitaMedica(citaMedicaExistente);
    }

}
