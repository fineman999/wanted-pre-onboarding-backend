package wanted.community.user.application;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.security.domain.CustomUserDetails;
import wanted.security.jwt.JwtGenerator;
import wanted.community.user.presentation.AuthenticationService;
import wanted.community.user.presentation.request.LoginRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    @Transactional
    @Override
    public String authenticate(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        if (authenticate.isAuthenticated()) {
            CustomUserDetails user = (CustomUserDetails) authenticate.getPrincipal();
            return jwtGenerator.generateToken(user);
        }

        throw new BadCredentialsException("사용자 인증정보가 일치하지 않습니다.");
    }
}
