package wanted.community.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.board.application.port.BoardRepository;
import wanted.community.board.domain.Board;
import wanted.community.board.domain.BoardCreateDto;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;

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
}
