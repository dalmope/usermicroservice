package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.ConsultorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IConsultorioRepository extends JpaRepository<ConsultorioEntity, Long> {
    boolean existsByIdAndEstado(Long id, Estado estado);
    List<ConsultorioEntity> findAllByEstado(Estado estado);
    Optional<ConsultorioEntity> findByIdAndEstado(Long id, Estado estado);
}
