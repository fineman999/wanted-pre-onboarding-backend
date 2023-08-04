package wanted.community.user.presentation.port;

import wanted.community.user.domain.User;
import wanted.community.user.presentation.request.UserCreateRequest;

public interface UserService {
    User create(UserCreateRequest userCreateRequest);
}
