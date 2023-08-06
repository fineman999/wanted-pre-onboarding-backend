package wanted.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Component
public class JwtConfig {

    private final String secret;
    private final Date expirationAccess;

    private final String permitUrlByUser;
    public JwtConfig(
            @Value("${jwt.secret-key}") String secret,
            @Value("${jwt.expiration-access}") String expirationAccess,
            @Value("${jwt.permit-url}") String permitUrlByUser) {
        this.secret = secret;
        this.expirationAccess = new Date(System.currentTimeMillis() + Long.parseLong(expirationAccess));
        this.permitUrlByUser = permitUrlByUser;
    }
}
