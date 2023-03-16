package co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.repository;

import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
