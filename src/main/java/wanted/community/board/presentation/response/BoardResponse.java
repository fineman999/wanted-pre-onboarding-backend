package wanted.community.board.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import wanted.community.board.domain.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    @Schema(description = "게시글 번호", example = "1")
    private final Long id;

    @Schema(description = "제목", example = "제목입니다.")
    private final String title;

    @Schema(description = "내용", example = "내용입니다.")
    private final String content;

    @Schema(description = "작성자 이메일", example = "test@naver.com")
    private final String writerEmail;

    @Schema(description = "작성일", example = "2021-08-23T00:00:00")
    private final LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2021-08-23T00:00:00")
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
