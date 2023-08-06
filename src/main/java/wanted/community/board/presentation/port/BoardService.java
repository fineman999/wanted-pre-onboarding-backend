package wanted.community.board.presentation.port;

import org.springframework.data.domain.Page;
import wanted.community.board.domain.Board;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.board.application.port.BoardPageDto;

public interface BoardService {
    Board save(BoardCreateDto boardCreateDto, String email);

    Page<Board> findAll(BoardPageDto boardPageDto);

    Board getById(Long id);
}
