package wanted.community.user.infrastructure.port;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.community.user.infrastructure.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
