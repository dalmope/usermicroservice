package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.IEspecialidadServicePort;
import co.dalmope.usermicroservice.domain.model.Especialidad;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.UtilRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidad")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EspecialidadController {

    private final IEspecialidadServicePort service;

    @GetMapping()
    public ResponseEntity<List<Especialidad>> getEspecialidades() {
       return new ResponseEntity<>(service.getAllActivos(), HttpStatus.OK);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<Especialidad>> getAllEspecialidades() {
       return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> saveEspecialidad(@RequestBody Especialidad especialidad) {
        service.create(especialidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<String> updateEspecialidad(@PathVariable Long id, @RequestBody Especialidad especialidad) {
        service.update(especialidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteEspecialidad(@PathVariable Long id) {
        service.desactive(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}