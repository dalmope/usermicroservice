package co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChangePasswordRequest {
    @NotBlank(message = "contraseña obligatoria")
    private String password;
    @NotBlank(message = "repetir contraseña")
    private String confirmPassword;
    @NotBlank(message = "token obligatorio")
    private String tokenPassword;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }
}
