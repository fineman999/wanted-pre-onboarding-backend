package wanted.community.board.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import wanted.community.board.application.port.BoardRepository;
import wanted.community.board.domain.Board;
import wanted.community.user.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class FakeBoardRepository implements BoardRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<Board> data = new ArrayList<>();


    @Override
    public Board save(Board board) {
        if (board.getId() == null || board.getId() == 0) {
            Board newBoard = Board.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .build();
            data.add(newBoard);
            return newBoard;
        }
        data.removeIf(item -> item.getId().equals(board.getId()));
        data.add(board);
        return board;
    }

    @Override
    public Page<Board> findAll(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Board getById(Long id) {
        return null;
    }
}
