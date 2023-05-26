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

import java.util.List;

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
    public ResponseEntity<String> saveConsultorio(@RequestBody Consultorio consultorio) {
        service.create(consultorio);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<String> updateConsultorio(@PathVariable Long id, @RequestBody Consultorio consultorio) {
        service.update(consultorio);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteConsultorio(@PathVariable Long id) {
        service.desactive(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}