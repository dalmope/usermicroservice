package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.domain.model.Estado;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
    List<RoleEntity> findAllByEstado(Estado estado);
    boolean existsByName(String name);
    boolean existsByIdAndEstado(Long id, Estado estado);
}
