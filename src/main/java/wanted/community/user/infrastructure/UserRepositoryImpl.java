package wanted.community.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;
import wanted.community.user.infrastructure.entity.UserEntity;
import wanted.community.user.infrastructure.port.UserJpaRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    @Override
    public User create(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserEntity::toModel);
    }

    @Override
    public User getByEmail(String email) {
        return findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("해당 이메일을 가진 유저가 없습니다.")
        );
    }
}
