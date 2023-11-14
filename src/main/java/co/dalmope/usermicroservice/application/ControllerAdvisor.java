package co.dalmope.usermicroservice.application;

import co.dalmope.usermicroservice.domain.exceptions.DateInvalidException;
import co.dalmope.usermicroservice.domain.exceptions.EspecialidadNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.ClinicaMedicaNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.ConsultorioNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.MailAlreadyExistsException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.RoleNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.NoDataFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.PersonAlreadyExistsException;
import co.dalmope.usermicroservice.domain.exceptions.PersonNotFoundException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.RoleNotAllowedForCreationException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.UserAlreadyExistsException;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.exception.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

import static co.dalmope.usermicroservice.application.Constants.CLINICA_MEDICA_NOT_FOUND_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.CONSULTORIO_NOT_FOUND_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.DATE_INVALID_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.ESPECIALIDAD_NOT_FOUND_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.MAIL_ALREADY_EXISTS_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.NO_DATA_FOUND_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.PERSON_ALREADY_EXISTS_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.PERSON_NOT_FOUND_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.RESPONSE_ERROR_MESSAGE_KEY;
import static co.dalmope.usermicroservice.application.Constants.ROLE_NOT_ALLOWED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.ROLE_NOT_FOUND_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.UNAUTHORIZED_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.USER_ALREADY_EXISTS_MESSAGE;
import static co.dalmope.usermicroservice.application.Constants.USER_NOT_FOUND_MESSAGE;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(ClinicaMedicaNotFoundException.class)
    public ResponseEntity<Map<String, String>> handler(
            ClinicaMedicaNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CLINICA_MEDICA_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(DateInvalidException.class)
    public ResponseEntity<Map<String, String>> handler(
            DateInvalidException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, DATE_INVALID_MESSAGE));
    }
    @ExceptionHandler(ConsultorioNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleConsultorioNotFoundException(
            ConsultorioNotFoundException consultorioNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CONSULTORIO_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(EspecialidadNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEspecialidadNotFoundException(
            EspecialidadNotFoundException especialidadNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ESPECIALIDAD_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_DATA_FOUND_MESSAGE));
    }
    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handlePersonAlreadyExistsException(
            PersonAlreadyExistsException personAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PERSON_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(MailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMailAlreadyExistsException(
            MailAlreadyExistsException mailAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, MAIL_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePersonNotFoundException(
            PersonNotFoundException personNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PERSON_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(RoleNotAllowedForCreationException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotAllowedForCreationException(
            RoleNotAllowedForCreationException roleNotAllowedForCreationException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_ALLOWED_MESSAGE));
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(
            RoleNotFoundException roleNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<Map<String, String>> handleAuthtorizationExepction(
            Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, UNAUTHORIZED_MESSAGE));
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Map<String, Object>> handleUnexpectedTypeException(
            MethodArgumentNotValidException ex) {
        Object errorMsg = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, errorMsg));
    }
}
