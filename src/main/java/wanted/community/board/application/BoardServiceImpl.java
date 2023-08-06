package wanted.community.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.board.application.port.BoardRepository;
import wanted.community.board.domain.Board;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.board.application.port.BoardPageDto;
import wanted.community.board.application.port.BoardUpdateDto;
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

    @Transactional(readOnly = true)
    @Override
    public Page<Board> findAll(BoardPageDto boardPageDto) {
        return boardRepository.findAll(getPage(boardPageDto));
    }

    @Transactional(readOnly = true)
    @Override
    public Board getById(Long id) {
        return boardRepository.getById(id);
    }

    @Transactional
    @Override
    public Board updateById(Long id, BoardUpdateDto boardUpdateDto, String email) {
        User user = userRepository.getByEmail(email);
        Board board = boardRepository.getById(id);

        checkEquals(user, board);
        board = board.update(boardUpdateDto);
        int update = boardRepository.update(board);

        checkSuccess(update);
        return board;
    }

    @Transactional
    @Override
    public void deleteById(Long id, String email) {
        User user = userRepository.getByEmail(email);
        Board board = boardRepository.getById(id);
        if (!user.compare(board.getWriter())) {
            throw new IllegalArgumentException("해당 게시글을 삭제할 권한이 없습니다.");
        }
        boardRepository.getById(id);
        boardRepository.deleteById(id);
    }

    private PageRequest getPage(BoardPageDto boardPageDto) {
        return PageRequest.of(boardPageDto.getPage(), boardPageDto.getSize());
    }

    private static void checkSuccess(int update) {
        if (update != 1) {
            throw new IllegalArgumentException("해당 게시글이 수정되지 않았습니다. 다시 시도해주세요.");
        }
    }

    private void checkEquals(User user, Board board) {
        if (!user.compare(board.getWriter())) {
            throw new IllegalArgumentException("해당 게시글을 수정할 권한이 없습니다.");
        }
    }

}
