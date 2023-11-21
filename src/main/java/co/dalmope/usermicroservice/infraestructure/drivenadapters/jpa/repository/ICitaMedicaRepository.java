package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.domain.model.EstadoCita;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICitaMedicaRepository extends JpaRepository<CitaMedicaEntity, Long> {
    List<CitaMedicaEntity> findAllByPacienteId(Long id);
    List<CitaMedicaEntity> findAllByEstado(EstadoCita estado);
}
