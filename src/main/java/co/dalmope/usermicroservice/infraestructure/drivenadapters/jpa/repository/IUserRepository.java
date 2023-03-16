package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPersonEntityIdAndRoleEntityId(Long idPerson, Long idRole);
    void deleteByPersonEntityIdAndRoleEntityId(Long idPerson, Long idRole);
    List<UserEntity> findAllByRoleEntityId(Long idRole, Pageable pageable);
    List<UserEntity> findAllByPersonEntityId(Long idPerson);
}
