package wanted.common.security.application;

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
}
