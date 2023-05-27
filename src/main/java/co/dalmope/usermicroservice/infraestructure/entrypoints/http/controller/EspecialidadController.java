package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.IEspecialidadServicePort;
import co.dalmope.usermicroservice.domain.model.Especialidad;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static co.dalmope.usermicroservice.application.Constants.CONSULTORIO_CREATED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.CONSULTORIO_DELETE_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.CONSULTORIO_UPDATE_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.RESPONSE_MESSAGE_KEY;

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
    public ResponseEntity<Map<String,String>> saveEspecialidad(@RequestBody Especialidad especialidad) {
        service.create(especialidad);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CONSULTORIO_CREATED_MESSAGE));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Map<String,String>> updateEspecialidad(@PathVariable Long id, @RequestBody Especialidad especialidad) {
        especialidad.setId(id);
        service.update(especialidad);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CONSULTORIO_UPDATE_MESSAGE));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Map<String,String>> deleteEspecialidad(@PathVariable Long id) {
        service.desactive(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CONSULTORIO_DELETE_MESSAGE));
    }
}