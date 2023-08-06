package wanted.community.user.application;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.user.application.port.JwtGenerator;
import wanted.community.user.presentation.port.AuthenticationService;
import wanted.community.user.presentation.request.LoginRequest;
import wanted.security.domain.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
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

    @Override
    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
