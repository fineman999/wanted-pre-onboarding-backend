package wanted.common.security.application;

import org.springframework.security.core.userdetails.UserDetails;
import wanted.community.user.application.port.JwtGenerator;
import wanted.security.domain.CustomUserDetails;

import java.util.Map;

public class FakeJwtGenerator implements JwtGenerator {

    private final String token;

    public FakeJwtGenerator(String token) {
        this.token = token;
    }

    @Override
    public String generateToken(CustomUserDetails user) {
        return token;
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, CustomUserDetails user) {
        return token;
    }

    @Override
    public String extractUsername(String token) {
        return null;
    }

    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        return false;
    }
}
