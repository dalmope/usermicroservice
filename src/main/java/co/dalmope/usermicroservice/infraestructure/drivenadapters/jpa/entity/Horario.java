package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity;

import co.dalmope.usermicroservice.domain.model.Estado;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "horario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Horario {
    @Id
    private Long id;
    private DiaSemana diaSemana;
    private Time horaInicio;
    private Time horaFin;
    private Estado estado;
    @ManyToOne
    private Cronograma cronograma;
    @ManyToOne
    private PersonEntity medico;
}
