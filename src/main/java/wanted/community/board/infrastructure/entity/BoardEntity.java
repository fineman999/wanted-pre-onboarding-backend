package wanted.community.board.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import wanted.community.board.domain.Board;
import wanted.community.user.infrastructure.entity.UserEntity;

@Getter
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    public static BoardEntity from(Board board) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.id = board.getId();
        boardEntity.title = board.getTitle();
        boardEntity.content = board.getContent();
        boardEntity.writer = UserEntity.from(board.getWriter());
        return boardEntity;
    }

    public Board toModel() {
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer.toModel())
                .build();
    }
}
