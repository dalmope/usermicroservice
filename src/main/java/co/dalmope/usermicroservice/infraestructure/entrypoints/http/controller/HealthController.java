package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import static co.dalmope.usermicroservice.application.Constants.PERSON_CREATED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.RESPONSE_MESSAGE_KEY;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HealthController {

    private final Logger logger = Logger.getLogger(HealthController.class.getName());
    private final IPersonServicePort personServicePort;
    @GetMapping()
    public ResponseEntity<Map<String,String>> health() {
        logger.info("Health check");
        personServicePort.getPerson(1L);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, PERSON_CREATED_MESSAGE));
    }
    
}
