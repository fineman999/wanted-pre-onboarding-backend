package wanted.community.user.presentation.port;

import wanted.community.user.presentation.request.LoginRequest;

public interface AuthenticationService {
    String authenticate(LoginRequest request);

    String getEmail();
}
