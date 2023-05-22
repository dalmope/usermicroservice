package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.model.Especialidad;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter.EspecialidadAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidad")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EspecialidadController {

    private final EspecialidadAdapter especialidadAdapter;

    @GetMapping()
    public ResponseEntity<List<Especialidad>> getEspecialidades() {
       return new ResponseEntity<>(especialidadAdapter.getEspecialidades(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> saveEspecialidad(Especialidad especialidad) {
        especialidadAdapter.saveEspecialidad(especialidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<String> updateEspecialidad(@PathVariable Long id, Especialidad especialidad) {
        especialidadAdapter.saveEspecialidad(especialidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
