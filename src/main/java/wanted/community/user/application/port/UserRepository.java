package wanted.community.user.application.port;

import wanted.community.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByEmail(String email);

    User getByEmail(String email);
}
