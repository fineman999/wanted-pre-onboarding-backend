package wanted.community.board.infrastructure.port;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.community.board.infrastructure.entity.BoardEntity;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {
}
