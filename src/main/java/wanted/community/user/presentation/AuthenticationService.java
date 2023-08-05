package wanted.community.user.presentation;

import wanted.community.user.presentation.request.LoginRequest;

public interface AuthenticationService {
    String authenticate(LoginRequest request);

    String getEmail();
}
