package wanted.community.board.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Board {
    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;

    @Builder
    public Board(Long id, String title, String content, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }


    public static Board create(BoardCreateDto boardCreateDto, Long userId) {
        return Board.builder()
                .title(boardCreateDto.getTitle())
                .content(boardCreateDto.getContent())
                .userId(userId)
                .build();
    }
}
