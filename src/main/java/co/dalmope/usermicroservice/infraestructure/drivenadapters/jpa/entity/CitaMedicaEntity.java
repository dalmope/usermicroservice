package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity;

import co.dalmope.usermicroservice.domain.model.EstadoCita;
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
@Table(name = "cita_medica")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CitaMedicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private String hora;
    private String motivo;
    private EstadoCita estado;
    private String idPaciente;
    private String idMedico;
    private String idEspecialidad;
    private String idConsultorio;
}
