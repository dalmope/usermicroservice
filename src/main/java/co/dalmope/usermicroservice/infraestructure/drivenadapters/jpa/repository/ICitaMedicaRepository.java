package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitaMedicaRepository extends JpaRepository<CitaMedicaEntity, Long> {
}
