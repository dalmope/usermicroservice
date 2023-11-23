package co.dalmope.usermicroservice.infraestructure.entrypoints.http.controller;

import co.dalmope.usermicroservice.application.security.jwt.JwtProvider;
import co.dalmope.usermicroservice.domain.api.IEmailServicePort;
import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.ChangePasswordRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.LoginUsuario;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.request.RecoveryPasswordRequest;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response.JwtDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Map;

import static co.dalmope.usermicroservice.application.Constants.EMAIL_RECOVERY_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.PASSWORD_CHANGED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.RESPONSE_MESSAGE_KEY;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final IEmailServicePort emailService;
    private final IPersonServicePort personServicePort;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new IllegalArgumentException("campos vacíos o email inválido");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getUserDni(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDTO = new JwtDto(jwt);
        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping("/recovery")
    public ResponseEntity<Map<String, String>> recovery(@RequestBody RecoveryPasswordRequest request) {
        emailService.sendEmailRecoveryPassword(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(RESPONSE_MESSAGE_KEY, EMAIL_RECOVERY_MESSAGE));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody ChangePasswordRequest request) {
        personServicePort.changePassword(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(RESPONSE_MESSAGE_KEY, PASSWORD_CHANGED_MESSAGE));
    }
}
