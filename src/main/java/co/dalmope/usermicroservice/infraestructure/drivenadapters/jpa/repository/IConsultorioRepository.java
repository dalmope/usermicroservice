package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.ConsultorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultorioRepository extends JpaRepository<ConsultorioEntity, Long> {
    boolean existsByIdAndEstado(Long id, Estado estado);
}
