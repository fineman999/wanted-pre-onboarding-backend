package wanted.community.board.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wanted.community.board.application.port.BoardRepository;
import wanted.community.board.domain.Board;
import wanted.community.board.infrastructure.entity.BoardEntity;
import wanted.community.board.infrastructure.port.BoardJpaRepository;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;
    @Override
    public Board save(Board board) {
        return boardJpaRepository.save(BoardEntity.from(board)).toModel();
    }
}
