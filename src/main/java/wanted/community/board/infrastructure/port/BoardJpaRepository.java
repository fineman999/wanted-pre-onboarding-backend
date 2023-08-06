package wanted.community.board.infrastructure.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wanted.community.board.infrastructure.entity.BoardEntity;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {



    @Query("select b from board b join fetch b.writer")
    Page<BoardEntity> findAllBoardFetchJoinUser(PageRequest pageRequest);
}
