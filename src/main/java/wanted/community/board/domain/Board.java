package wanted.community.board.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import wanted.community.user.domain.User;

@Getter
@ToString
@EqualsAndHashCode
public class Board {
    private final Long id;
    private final String title;
    private final String content;
    private final User writer;

    @Builder
    public Board(Long id, String title, String content, User writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }


    public static Board create(BoardCreateDto boardCreateDto, User user) {
        checkValid(boardCreateDto);
        return Board.builder()
                .title(boardCreateDto.getTitle())
                .content(boardCreateDto.getContent())
                .writer(user)
                .build();
    }

    private static void checkValid(BoardCreateDto boardCreateDto) {
        checkValidTitle(boardCreateDto.getTitle());
        checkValidContent(boardCreateDto.getContent());
    }

    private static void checkValidTitle(String title) {
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new IllegalArgumentException("제목이 비어있습니다.");
        }
    }
    private static void checkValidContent(String content) {
        if (content == null || content.isEmpty() || content.isBlank()) {
            throw new IllegalArgumentException("내용이 비어있습니다.");
        }
    }
}
