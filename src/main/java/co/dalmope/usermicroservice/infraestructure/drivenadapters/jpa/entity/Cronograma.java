package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "cronograma")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cronograma {
    @Id
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
}
