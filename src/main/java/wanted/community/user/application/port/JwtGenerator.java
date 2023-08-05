package wanted.community.user.application.port;

import wanted.security.domain.CustomUserDetails;

import java.util.Map;

public interface JwtGenerator {
    String generateToken(CustomUserDetails user);

    String generateToken(
            Map<String, Object> extraClaims,
            CustomUserDetails user
    );
}
