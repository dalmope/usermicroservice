package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoleResponse {
    private Long id;
    private String name;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }
}
