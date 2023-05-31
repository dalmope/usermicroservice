package co.dalmope.usermicroservice.domain.spi;

import co.dalmope.usermicroservice.domain.model.Consultorio;
import co.dalmope.usermicroservice.domain.model.Estado;

import java.util.List;

public interface IConsultorioPersistencePort {
    void saveConsultorio(Consultorio consultorio);
    List<Consultorio> getAllConsultorios();
    boolean existsConsultorio(Long id);
    boolean existsAndIsActive(Long id);
    Consultorio getConsultorio(Long id);
    List<Consultorio> getAllConsultoriosActivos();
    Consultorio getConsultorioByIdAndEstado(Long id, Estado estado);
}
