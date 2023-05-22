package co.dalmope.usermicroservice.domain.api;

import co.dalmope.usermicroservice.domain.model.Consultorio;
import java.util.List;

public interface IConsultorioServicePort {

    void create(Consultorio consultorio);

    void update(Consultorio consultorio);

    void desactive(Long id);

    List<Consultorio> getAll();

    List<Consultorio> getAllActivos();
}
