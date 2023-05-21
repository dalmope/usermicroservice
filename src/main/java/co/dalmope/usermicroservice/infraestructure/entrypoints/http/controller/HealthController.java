package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.logging.Logger;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HealthController {

    private final Logger logger = Logger.getLogger(HealthController.class.getName());
    private final IPersonServicePort personServicePort;
    @GetMapping()
    public ResponseEntity<String> health() {
        logger.info("Health check");
        personServicePort.getPerson(1L);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
}
