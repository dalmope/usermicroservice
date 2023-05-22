package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadRepository extends JpaRepository<EspecialidadEntity, Long> {
    boolean existsByIdAndEstado(Long id, Estado estado);
}
