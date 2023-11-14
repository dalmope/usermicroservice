package co.dalmope.usermicroservice.application.security;

import co.dalmope.usermicroservice.application.security.jwt.JwtEntryPoint;
import co.dalmope.usermicroservice.application.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MainSecurity {
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtTokenFilter jwtTokenFilter;
    private static final String ROLE_ADMIN = "ADMIN";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers("/auth/login", "/auth/message", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/health", "/person/").permitAll()
                    .requestMatchers("/cita-medica/paciente/**", "/role/especialidad/user").hasAnyRole(ROLE_ADMIN, "USER", "MED", "SECRETARIO")
                    .requestMatchers("/user/**", "/cita-medica/**", "/especialidad/all").hasAnyRole(ROLE_ADMIN, "MED", "SECRETARIO")
                    .requestMatchers( "/role/**", "/consultorio/**").hasRole(ROLE_ADMIN)
                    .requestMatchers("/health/**").permitAll()
                     .anyRequest().authenticated()
                )
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
