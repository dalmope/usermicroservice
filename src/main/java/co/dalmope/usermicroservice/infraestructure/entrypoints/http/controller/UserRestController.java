package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.infraestructure.entrypoints.http.mapper.IUserRequestMapper;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.UserRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response.PersonResponse;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.mapper.IPersonResponseMapper;
import co.dalmope.usermicroservice.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static co.dalmope.usermicroservice.application.Constants.RESPONSE_MESSAGE_KEY;
import static co.dalmope.usermicroservice.application.Constants.USER_CREATED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.USER_DELETED_MESSAGE;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IPersonResponseMapper personResponseMapper;

    @Operation(summary = "Add a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "User already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for user creation",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("")
    public ResponseEntity<Map<String, String>> saveUser(@RequestBody UserRequest userRequest) {
        userServicePort.saveUser(userRequestMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_CREATED_MESSAGE));
    }
    @Operation(summary = "Delete an user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @DeleteMapping("")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestBody UserRequest userRequest) {
        userServicePort.deleteUser(userRequestMapper.toUser(userRequest));
        return ResponseEntity.ok(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_DELETED_MESSAGE));
    }
    @Operation(summary = "Get all Meds",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All Meds returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonResponse.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/medico")
    public ResponseEntity<List<PersonResponse>> getAllMeds() {
        return ResponseEntity.ok(personResponseMapper.toPersonResponseList(userServicePort.getAllMeds()));
    }
    @Operation(summary = "Get a provider user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Provider user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with provider role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/medico/{id}")
    public ResponseEntity<PersonResponse> getProvider(@PathVariable Long id) {
        return ResponseEntity.ok(personResponseMapper.userToPersonResponse(userServicePort.getMed(id)));
    }
    @Operation(summary = "Get a employee user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with employee role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/employee/{id}")
    public ResponseEntity<PersonResponse> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(personResponseMapper.userToPersonResponse(userServicePort.getEmployee(id)));
    }
    @Operation(summary = "Get a client user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with client role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/client/{id}")
    public ResponseEntity<PersonResponse> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(personResponseMapper.userToPersonResponse(userServicePort.getClient(id)));
    }
}
