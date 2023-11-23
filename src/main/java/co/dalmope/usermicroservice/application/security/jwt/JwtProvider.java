package co.dalmope.usermicroservice.application.security.jwt;

import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.PrincipalUser;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import co.dalmope.usermicroservice.infraestructure.entrypoints.http.dto.response.JwtDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.expiration-email-token}")
    private int expirationEmailToken;

    private static final String ROLES_NAME = "roles";

    public String generateToken(Authentication authentication) {
        PrincipalUser usuarioPrincipal = (PrincipalUser) authentication.getPrincipal();
        List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername())
                .claim(ROLES_NAME, roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 180L))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String generateToken(String nombreUsuario) {
        return Jwts.builder()
                .setSubject(nombreUsuario)
                .claim(ROLES_NAME, List.of("ROLE_USER"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationEmailToken * 180L))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            log.error("token no soportado");
        } catch (ExpiredJwtException e) {
            log.error("token expirado");
        } catch (IllegalArgumentException e) {
            log.error("token vacío");
        } catch (SignatureException e) {
            log.error("fail en la firma");
        } catch (Exception e) {
            log.error(String.valueOf(e));
            log.error("error desconocido");
        }
        return false;
    }

    public String refreshToken(JwtDto jwtDto) throws ParseException {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(jwtDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String nombreUsuario = claims.getSubject();
            List<String> roles = claims.getStringListClaim(ROLES_NAME);

            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim(ROLES_NAME, roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .compact();
        }
        return null;
    }

}