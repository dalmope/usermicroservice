package co.dalmope.usermicroservice.infraestructure.entryPoints.http.controller;

import co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.request.PersonRequest;
import co.dalmope.usermicroservice.infraestructure.entryPoints.http.mapper.IPersonRequestMapper;
import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static co.dalmope.usermicroservice.application.Constants.PERSON_CREATED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.RESPONSE_MESSAGE_KEY;

@RestController
@RequestMapping("/person/")
@RequiredArgsConstructor
public class PersonRestController {
    private final IPersonServicePort personServicePort;
    private final IPersonRequestMapper personRequestMapper;

    @Operation(summary = "Add a new person",
            responses = {
                @ApiResponse(responseCode = "201", description = "Person created",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                @ApiResponse(responseCode = "409", description = "Person already exists",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping
    public ResponseEntity<Map<String, String>> savePerson(@RequestBody PersonRequest personRequest) {
        personServicePort.savePerson(personRequestMapper.toPerson(personRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, PERSON_CREATED_MESSAGE));
    }
}
