package wanted.community.user.application.port;

import wanted.community.user.domain.User;

public interface UserRepository {
    User create(User userCreateRequest);
}
