package wanted.community.board.application.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import wanted.community.board.domain.Board;

public interface BoardRepository {
    Board save(Board board);

    Page<Board> findAll(PageRequest pageRequest);

    Board getById(Long id);

    void deleteById(Long id);

    int update(Board board);
}
