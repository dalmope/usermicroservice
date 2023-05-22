package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.Consultorio;
import co.dalmope.usermicroservice.domain.spi.IConsultorioPersistencePort;

public interface IConsultorioServicePort {

    void create(Consultorio consultorio);

    void update(Consultorio consultorio);

    void desactive(Long id);
}
