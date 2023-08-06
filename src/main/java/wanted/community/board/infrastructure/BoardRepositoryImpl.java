package wanted.community.board.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import wanted.community.board.application.port.BoardRepository;
import wanted.community.board.domain.Board;
import wanted.community.board.infrastructure.entity.BoardEntity;
import wanted.community.board.infrastructure.port.BoardJpaRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;
    @Override
    public Board save(Board board) {
        return boardJpaRepository.save(BoardEntity.from(board)).toModel();
    }

    @Override
    public Page<Board> findAll(PageRequest pageRequest) {
        return boardJpaRepository.findAllBoardFetchJoinUser(pageRequest).map(BoardEntity::toModel);
    }

    @Override
    public Board getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    }

    @Override
    public void deleteById(Long id) {
        boardJpaRepository.deleteById(id);
    }

    private Optional<Board> findById(Long id) {
        return boardJpaRepository.findById(id).map(BoardEntity::toModel);
    }
}
