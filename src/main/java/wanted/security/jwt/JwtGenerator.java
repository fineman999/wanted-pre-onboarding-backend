package wanted.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wanted.security.domain.CustomUserDetails;
import wanted.security.jwt.port.ClockHolder;

import java.security.Key;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private final ClockHolder clockHolder;
    private final JwtConfig jwtConfig;

    public String generateToken(CustomUserDetails user) {
        return generateToken(Map.of(), user);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            CustomUserDetails user
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(clockHolder.now())
                .setExpiration(jwtConfig.getExpirationAccess())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
