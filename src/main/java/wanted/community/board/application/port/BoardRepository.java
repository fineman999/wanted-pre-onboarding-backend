package wanted.community.board.application.port;

import wanted.community.board.domain.Board;

public interface BoardRepository {
    Board save(Board board);
}
