package wanted.community.board.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.board.application.port.BoardUpdateDto;
import wanted.community.user.domain.User;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
public class Board {
    private final Long id;
    private final String title;
    private final String content;
    private final User writer;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public Board(Long id, String title, String content, User writer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Board update(BoardUpdateDto boardUpdateDto) {
        return Board.builder()
                .id(this.id)
                .title(boardUpdateDto.getTitle() == null ? this.title : boardUpdateDto.getTitle())
                .content(boardUpdateDto.getContent() == null ? this.content : boardUpdateDto.getContent())
                .writer(this.writer)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
