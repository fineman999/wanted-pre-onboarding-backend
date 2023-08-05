package wanted.community.user.application.port;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import wanted.security.domain.CustomUserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtGenerator {
    String generateToken(CustomUserDetails user);

    String generateToken(
            Map<String, Object> extraClaims,
            CustomUserDetails user
    );

    String extractUsername(String token);

    boolean isTokenValid(String jwt, UserDetails userDetails);
}
