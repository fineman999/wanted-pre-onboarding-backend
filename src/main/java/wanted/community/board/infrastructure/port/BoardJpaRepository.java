package wanted.community.board.infrastructure.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import wanted.community.board.infrastructure.entity.BoardEntity;

import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {


    @Query("select b from board b join fetch b.writer where b.id = :id")
    Optional<BoardEntity> findByIdFetchJoin(Long id);

    @Query("select b from board b join fetch b.writer")
    Page<BoardEntity> findAllBoardFetchJoinUser(PageRequest pageRequest);

    @Modifying
    @Query("update board b set b.title = :#{#from.title}, b.content = :#{#from.content} where b.id = :#{#from.id}")
    int update(BoardEntity from);
}
