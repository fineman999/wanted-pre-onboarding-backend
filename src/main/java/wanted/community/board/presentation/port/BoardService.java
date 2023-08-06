package wanted.community.board.presentation.port;

import org.springframework.data.domain.Page;
import wanted.community.board.domain.Board;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.board.application.port.BoardPageDto;
import wanted.community.board.application.port.BoardUpdateDto;

public interface BoardService {
    Board save(BoardCreateDto boardCreateDto, String email);

    Page<Board> findAll(BoardPageDto boardPageDto);

    Board getById(Long id);


    Board updateById(Long id, BoardUpdateDto boardUpdateDto, String email);

    void deleteById(Long id, String email);
}
