package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity;

import co.dalmope.usermicroservice.domain.model.Estado;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consultorio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultorioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Estado estado;
}