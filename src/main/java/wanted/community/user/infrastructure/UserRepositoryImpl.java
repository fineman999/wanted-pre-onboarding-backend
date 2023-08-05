package wanted.community.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;
import wanted.community.user.infrastructure.entity.UserEntity;
import wanted.community.user.infrastructure.port.UserJpaRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    @Override
    public User create(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }
}
