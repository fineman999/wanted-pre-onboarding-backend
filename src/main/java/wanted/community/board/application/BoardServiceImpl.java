package wanted.community.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.board.application.port.BoardRepository;
import wanted.community.board.domain.Board;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.board.presentation.request.BoardPageRequest;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Board save(BoardCreateDto boardCreateDto, String email) {
        User user = userRepository.getByEmail(email);
        Board board = Board.create(boardCreateDto, user);
        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Board> findAll(BoardPageRequest boardPageRequest) {
        return boardRepository.findAll(getPage(boardPageRequest));
    }

    @Override
    public Board getById(Long id) {
        return boardRepository.getById(id);
    }

    private PageRequest getPage(BoardPageRequest boardPageRequest) {
        return PageRequest.of(boardPageRequest.getPage(), boardPageRequest.getSize());
    }
}
