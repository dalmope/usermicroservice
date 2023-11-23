package co.dalmope.usermicroservice.application;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long ADMIN_ROLE_ID = 1L;
    public static final Long USER_ROLE_ID = 2L;
    public static final Long MED_ROLE_ID = 3L;
    public static final Long SECRETARIO_ROLE_ID = 4L;
    public static final int MAX_PAGE_SIZE = 2;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String PERSON_CREATED_MESSAGE = "Person created successfully";
    public static final String CONSULTORIO_CREATED_MESSAGE = "Consultorio creado exitosamente";
    public static final String CONSULTORIO_UPDATE_MESSAGE = "Consultorio actualizado exitosamente";
    public static final String CONSULTORIO_DELETE_MESSAGE = "Consultorio eliminado exitosamente";
    public static final String CONSULTORIO_NOT_FOUND_MESSAGE = "Consultorio no encontrado";
    public static final String DATE_INVALID_MESSAGE = "La fecha debe ser mayor a la actual";
    public static final String ESPECIALIDAD_CREATED_MESSAGE = "Especialidad creada exitosamente";
    public static final String ESPECIALIDAD_UPDATE_MESSAGE = "Especialidad actualizada exitosamente";
    public static final String ESPECIALIDAD_DELETE_MESSAGE = "Especialidad eliminada exitosamente";
    public static final String ESPECIALIDAD_NOT_FOUND_MESSAGE = "Especialidad no encontrada";
    public static final String CLINICA_MEDICA_NOT_FOUND_MESSAGE = "Clinica medica no encontrada";
    public static final String CITA_MEDICA_ASIGNADA_MESSAGE = "Cita medica asignada exitosamente";
    public static final String CITA_MEDICA_CREADA_MESSAGE = "Cita medica actualizada exitosamente";
    public static final String CITA_MEDICA_DELETE_MESSAGE = "Cita medica eliminada exitosamente";
    public static final String PASSWORD_CHANGED_MESSAGE = "Password changed successfully";
    public static final String HEALTH_MESSAGE = "Microservice is up and running";
    public static final String USER_CREATED_MESSAGE = "User created successfully";
    public static final String USER_DELETED_MESSAGE = "User deleted successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String PERSON_ALREADY_EXISTS_MESSAGE = "A person already exists with the DNI number provided";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A person with that mail already exists";
    public static final String PERSON_NOT_FOUND_MESSAGE = "No person found with the id provided";
    public static final String ROLE_CREATED_MESSAGE = "Rol creado exitosamente";
    public static final String ROLE_UPDATED_MESSAGE = "Rol actualizado exitosamente";
    public static final String ROLE_NOT_FOUND_MESSAGE = "No role found with the id provided";
    public static final String UNAUTHORIZED_MESSAGE = "Your credentials are not valid or you don't have permission to access this resource";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create users with this role";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with the role provided";
    public static final String USER_NOT_FOUND_MESSAGE = "No user found with the role provided";
    public static final String SWAGGER_TITLE_MESSAGE = "User API";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
