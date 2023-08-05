package wanted.community.user.infrastructure.port;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.community.user.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
