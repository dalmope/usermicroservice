package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.CitaMedica;

public interface ICitaMedicaPersistencePort {
    void saveCitaMedica(CitaMedica citaMedica);
}
