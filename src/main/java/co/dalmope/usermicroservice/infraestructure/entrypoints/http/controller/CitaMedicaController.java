package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.ICitaMedicaServicePort;
import co.dalmope.usermicroservice.domain.model.EstadoCita;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.AsignarCitaRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.PedirCitaMedicaRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response.CitaMedicaReponse;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.mapper.CitaMedicaRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static co.dalmope.usermicroservice.application.Constants.*;

@RestController
@RequestMapping("/cita-medica")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CitaMedicaController {

    private final ICitaMedicaServicePort service;
    private final CitaMedicaRequestMapper citaMedicaRequestMapper;

    @GetMapping()
    public ResponseEntity<List<CitaMedicaReponse>> getAllCitasMedica() {
        return new ResponseEntity<>(citaMedicaRequestMapper.toCitaMedicaResponseList(service.getAll()), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CitaMedicaReponse>> getCitasByEstado(@PathVariable EstadoCita estado) {
        return new ResponseEntity<>(citaMedicaRequestMapper.toCitaMedicaResponseList(service.getAllByEstado(estado)), HttpStatus.OK);
    }

    @PostMapping("/paciente")
    public ResponseEntity<Map<String, String>> saveCitaMedica(@RequestBody PedirCitaMedicaRequest citaMedica) {
        service.create(citaMedicaRequestMapper.toUser(citaMedica));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CITA_MEDICA_CREADA_MESSAGE));
    }

    @GetMapping("/paciente/{codigo}")
    public ResponseEntity<List<CitaMedicaReponse>> getAllCitasMedicaByCodigo(@PathVariable String codigo) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(citaMedicaRequestMapper.toCitaMedicaResponseList(service.getAllByCodigo(codigo)));
    }

    @PostMapping("/asignar")
    public ResponseEntity<Map<String, String>> asignarCitaMedica(@RequestBody AsignarCitaRequest citaMedica) {
        service.asignarCitaMedica(citaMedicaRequestMapper.toCitaMedica(citaMedica));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CITA_MEDICA_ASIGNADA_MESSAGE));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCitaMedica(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, CITA_MEDICA_DELETE_MESSAGE));
    }

}
