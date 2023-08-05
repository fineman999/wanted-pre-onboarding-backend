package wanted.community.board.presentation.port;

import wanted.community.board.domain.Board;
import wanted.community.board.domain.BoardCreateDto;

public interface BoardService {
    Board save(BoardCreateDto boardCreateDto, String email);
}
