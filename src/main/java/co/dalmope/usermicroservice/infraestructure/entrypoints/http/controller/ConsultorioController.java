package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.IConsultorioServicePort;
import co.dalmope.usermicroservice.domain.model.Consultorio;
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
import static co.dalmope.usermicroservice.application.Constants.HEALTH_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.RESPONSE_MESSAGE_KEY;

@RestController
@RequestMapping("/consultorio")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConsultorioController {
    private final IConsultorioServicePort service;

    @GetMapping()
    public ResponseEntity<List<Consultorio>> getConsultorios() {
        return new ResponseEntity<>(service.getAllActivos(), HttpStatus.OK);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<Consultorio>> getAllConsultorios() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Map<String,String>> saveConsultorio(@RequestBody Consultorio consultorio) {
        service.create(consultorio);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CONSULTORIO_CREATED_MESSAGE));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Map<String,String>> updateConsultorio(@PathVariable Long id, @RequestBody Consultorio consultorio) {
        consultorio.setId(id);
        service.update(consultorio);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CONSULTORIO_UPDATE_MESSAGE));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Map<String,String>> deleteConsultorio(@PathVariable Long id) {
        service.desactive(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CONSULTORIO_DELETE_MESSAGE));
    }
}