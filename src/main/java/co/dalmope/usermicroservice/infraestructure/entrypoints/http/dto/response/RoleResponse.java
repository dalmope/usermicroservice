package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response;

import co.dalmope.usermicroservice.domain.model.Estado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class RoleResponse {
    private Long id;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    @Size(max = 50, message = "Description must be less than 50 characters")
    private String description;
    @Null
    private Estado estado;

    public void setId(Long id) {
        this.id = id;
    }
}
