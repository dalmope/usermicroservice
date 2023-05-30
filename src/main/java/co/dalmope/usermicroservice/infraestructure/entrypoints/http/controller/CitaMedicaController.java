package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.ICitaMedicaServicePort;
import co.dalmope.usermicroservice.domain.model.CitaMedica;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.AsignarCitaRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.PedirCitaMedicaRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.mapper.CitaMedicaRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cita-medica")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CitaMedicaController {

    private final ICitaMedicaServicePort service;
    private final CitaMedicaRequestMapper citaMedicaRequestMapper;

    @GetMapping()
    public ResponseEntity<List<CitaMedica>> getAllCitasMedica() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> saveCitaMedica(@RequestBody PedirCitaMedicaRequest citaMedica) {
        service.create(citaMedicaRequestMapper.toUser(citaMedica));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<CitaMedica>> getAllCitasMedicaByPaciente(@PathVariable Long id) {
        return new ResponseEntity<>(service.getAllByPaciente(id), HttpStatus.OK);
    }

    @PostMapping("/asignar")
    public ResponseEntity<Void> asignarCitaMedica(@RequestBody AsignarCitaRequest citaMedica) {
        service.asignarCitaMedica(citaMedicaRequestMapper.toCitaMedica(citaMedica));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
