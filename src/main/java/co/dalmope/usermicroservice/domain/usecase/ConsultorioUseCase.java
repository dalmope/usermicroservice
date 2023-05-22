package co.dalmope.usermicroservice.domain.usecase;

import co.dalmope.usermicroservice.domain.api.IConsultorioServicePort;
import co.dalmope.usermicroservice.domain.model.Consultorio;
import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.domain.spi.IConsultorioPersistencePort;

public class ConsultorioUseCase implements IConsultorioServicePort {

    private final IConsultorioPersistencePort consultorioRepository;

    public ConsultorioUseCase(IConsultorioPersistencePort consultorioRepository) {
        this.consultorioRepository = consultorioRepository;
    }

    @Override
    public void create(Consultorio consultorio) {
        consultorioRepository.saveConsultorio(consultorio);
    }

    @Override
    public void update(Consultorio consultorio) {
        consultorioRepository.saveConsultorio(consultorio);
    }

    @Override
    public void desactive(Long id) {
        Consultorio consultorio = consultorioRepository.getConsultorio(id);
        consultorio.setEstado(Estado.INACTIVO);
        consultorioRepository.saveConsultorio(consultorio);
    }
}
