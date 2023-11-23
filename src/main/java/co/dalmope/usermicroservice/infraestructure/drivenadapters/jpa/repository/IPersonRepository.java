package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository;

import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByDniNumber(String dniNumber);
    Optional<PersonEntity> findByTokenPassword(String tokenPassword);
    boolean existsByDniNumber(String dniNumber);
    boolean existsByMail(String mail);
}
