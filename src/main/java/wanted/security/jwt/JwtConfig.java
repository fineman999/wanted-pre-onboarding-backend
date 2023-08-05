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

    public JwtConfig(
            @Value("${security.jwt.secret-key}") String secret,
            @Value("${security.jwt.expiration-access}") String expirationAccess) {
        this.secret = secret;
        this.expirationAccess = new Date(System.currentTimeMillis() + Long.parseLong(expirationAccess));
    }
}
