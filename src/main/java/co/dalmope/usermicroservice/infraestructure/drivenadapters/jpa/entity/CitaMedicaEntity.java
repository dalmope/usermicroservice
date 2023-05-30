package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity;

import co.dalmope.usermicroservice.domain.model.EstadoCita;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "cita_medica")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CitaMedicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaHora;
    private String motivo;
    private EstadoCita estado = EstadoCita.POR_ASIGNAR;
    @ManyToOne
    private RoleEntity especialidad;
    @ManyToOne
    private ConsultorioEntity consultorio;
    @ManyToOne
    private PersonEntity paciente;
    @ManyToOne
    private PersonEntity medico;
}