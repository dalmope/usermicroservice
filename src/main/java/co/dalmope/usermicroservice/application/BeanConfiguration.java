package co.dalmope.usermicroservice.application;

import co.dalmope.usermicroservice.domain.api.ICitaMedicaServicePort;
import co.dalmope.usermicroservice.domain.api.IConsultorioServicePort;
import co.dalmope.usermicroservice.domain.api.IEspecialidadServicePort;
import co.dalmope.usermicroservice.domain.spi.ICitaMedicaPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IConsultorioPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IEspecialidadPersistencePort;
import co.dalmope.usermicroservice.domain.usecase.CitaMedicaUseCase;
import co.dalmope.usermicroservice.domain.usecase.ConsultorioUseCase;
import co.dalmope.usermicroservice.domain.usecase.EspecialidadUseCase;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter.CitaMedicaAdapter;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter.ConsultorioAdapter;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter.EspecialidadAdapter;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter.PersonMysqlAdapter;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter.RoleMysqlAdapter;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter.UserMysqlAdapter;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.ICitaMedicaMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IConsultorioMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IEspecialidadMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.ICitaMedicaRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IConsultorioRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IEspecialidadRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IPersonRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IUserRepository;
import co.dalmope.usermicroservice.domain.api.IPersonServicePort;
import co.dalmope.usermicroservice.domain.api.IRoleServicePort;
import co.dalmope.usermicroservice.domain.api.IUserServicePort;
import co.dalmope.usermicroservice.domain.spi.IPersonPersistencePort;
import co.dalmope.usermicroservice.domain.spi.IRolePersistencePort;
import co.dalmope.usermicroservice.domain.spi.IUserPersistencePort;
import co.dalmope.usermicroservice.domain.usecase.RoleUseCase;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IPersonEntityMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IRoleEntityMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper.IUserEntityMapper;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IRoleRepository;
import co.dalmope.usermicroservice.domain.usecase.PersonUseCase;
import co.dalmope.usermicroservice.domain.usecase.UserUseCase;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IPersonRepository personRepository;
    private final IUserRepository userRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IPersonEntityMapper personEntityMapper;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final IConsultorioRepository consultorioRepository;
    private final IConsultorioMapper consultorioMapper;
    private final IEspecialidadRepository especialidadRepository;
    private final IEspecialidadMapper especialidadMapper;
    private final ICitaMedicaMapper citaMedicaMapper;
    private final ICitaMedicaRepository citaMedicaRepository;

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }
    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleMysqlAdapter(roleRepository, roleEntityMapper);
    }
    @Bean
    public IPersonServicePort personServicePort() {
        return new PersonUseCase(personPersistencePort());
    }
    @Bean
    public IPersonPersistencePort personPersistencePort() {
        return new PersonMysqlAdapter(personRepository, personEntityMapper, passwordEncoder, userRepository, roleRepository);
    }
    @Bean IConsultorioPersistencePort consultorioPersistencePort() {
        return new ConsultorioAdapter(consultorioRepository, consultorioMapper);
    }
    @Bean IEspecialidadPersistencePort especialidadPersistencePort() {
        return new EspecialidadAdapter(especialidadRepository, especialidadMapper);
    }
    @Bean
    ICitaMedicaPersistencePort citaMedicaPersistencePort() {
        return new CitaMedicaAdapter(citaMedicaRepository, citaMedicaMapper);
    }
    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }
    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserMysqlAdapter(userRepository, personRepository, roleRepository, userEntityMapper);
    }
    @Bean
    public IConsultorioServicePort consultorioServicePort() {
        return new ConsultorioUseCase(consultorioPersistencePort());
    }
    @Bean
    IEspecialidadServicePort especialidadServicePort() {
        return new EspecialidadUseCase(especialidadPersistencePort());
    }
    @Bean
    ICitaMedicaServicePort citaMedicaServicePort() {
        return new CitaMedicaUseCase(citaMedicaPersistencePort(), rolePersistencePort(), consultorioPersistencePort(), personPersistencePort());
    }
    @Bean
    public EmailService emailService() {
        return new EmailService(javaMailSender);
    }
}
