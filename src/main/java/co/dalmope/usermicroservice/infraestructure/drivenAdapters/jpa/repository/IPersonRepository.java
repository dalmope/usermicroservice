package co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.repository;

import co.dalmope.usermicroservice.infraestructure.drivenAdapters.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByDniNumber(String dniNumber);

    Boolean existsByDniNumber(String dniNumber);

    boolean existsByMail(String mail);
}
