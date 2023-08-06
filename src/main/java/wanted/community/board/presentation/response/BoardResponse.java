package wanted.community.board.presentation.response;

import lombok.Builder;
import lombok.Getter;
import wanted.community.board.domain.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String writerEmail;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public BoardResponse(Long id, String title, String content, String writerEmail, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerEmail = writerEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writerEmail(board.getWriter().getEmail())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
