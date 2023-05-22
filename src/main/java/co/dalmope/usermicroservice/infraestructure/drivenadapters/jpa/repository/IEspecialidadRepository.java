package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEspecialidadRepository extends JpaRepository<EspecialidadEntity, Long> {
    boolean existsByIdAndEstado(Long id, Estado estado);
    List<EspecialidadEntity> findAllByEstado(Estado estado);
}
