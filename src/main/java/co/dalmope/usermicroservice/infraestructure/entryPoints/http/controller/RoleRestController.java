package co.dalmope.usermicroservice.infraestructure.entryPoints.http.controller;

import co.dalmope.usermicroservice.domain.api.IRoleServicePort;
import co.dalmope.usermicroservice.infraestructure.entryPoints.http.dto.response.RoleResponse;
import co.dalmope.usermicroservice.infraestructure.entryPoints.http.mapper.IRoleResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/role")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
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
    @GetMapping("")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(roleResponseMapper.toResponseList(roleServicePort.getAllRoles()));
    }
}
