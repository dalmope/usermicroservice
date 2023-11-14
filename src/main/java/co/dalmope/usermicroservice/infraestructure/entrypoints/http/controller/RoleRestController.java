package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.domain.api.IRoleServicePort;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response.RoleResponse;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.mapper.IRoleResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import static co.dalmope.usermicroservice.application.Constants.RESPONSE_MESSAGE_KEY;
import static co.dalmope.usermicroservice.application.Constants.ROLE_CREATED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.ROLE_UPDATED_MESSAGE;

@RestController()
@RequestMapping("/role")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
@CrossOrigin("*")
public class RoleRestController {
    private final IRoleResponseMapper roleResponseMapper;
    private final IRoleServicePort roleServicePort;

    @Operation(summary = "Get all the roles",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All roles returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RoleResponse.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping()
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(roleResponseMapper.toResponseList(roleServicePort.getAllRoles()));
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> createRole(@RequestBody RoleResponse roleResponse) {
        roleServicePort.create(roleResponseMapper.toDomain(roleResponse));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, ROLE_CREATED_MESSAGE));
    }

    @GetMapping("/especialidad/user")
    public ResponseEntity<List<RoleResponse>> getMed() {
        return ResponseEntity.ok(roleResponseMapper.toResponseList(roleServicePort.getActiveEspecialidades()));
    }

    @GetMapping("/especialidad/all")
    public ResponseEntity<List<RoleResponse>> getAllMeds() {
        return ResponseEntity.ok(roleResponseMapper.toResponseList(roleServicePort.getAllEspecialidades()));
    }

    @PutMapping("/especialidad")
    public ResponseEntity<Map<String, String>> updateMed(@RequestBody RoleResponse roleResponse) {
        roleServicePort.update(roleResponseMapper.toDomain(roleResponse));
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, ROLE_UPDATED_MESSAGE));
    }

    @DeleteMapping("/especialidad/{id}")
    public ResponseEntity<Map<String, String>> deleteMed(@PathVariable("id") Long id) {
        roleServicePort.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, ROLE_UPDATED_MESSAGE));
    }

}
