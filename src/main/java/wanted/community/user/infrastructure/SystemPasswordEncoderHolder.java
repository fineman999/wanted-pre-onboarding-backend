package wanted.community.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import wanted.community.user.application.port.PasswordEncoderHolder;

@Component
@RequiredArgsConstructor
public class SystemPasswordEncoderHolder implements PasswordEncoderHolder {

    private final PasswordEncoder passwordEncoder;
    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
