package wanted.community.board.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import wanted.community.board.domain.Board;


@Getter
public class BoardPageResponse {

    @Schema(description = "게시글 번호", example = "1")
    private final Long id;

    @Schema(description = "제목", example = "제목입니다.")
    private final String title;

    @Schema(description = "작성자 이메일", example = "test@naver.com")
    private final String writerEmail;

    @Builder
    public BoardPageResponse(Long id, String title, String writerEmail) {
        this.id = id;
        this.title = title;
        this.writerEmail = writerEmail;
    }

    public static BoardPageResponse from(Board board) {
        return BoardPageResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .writerEmail(board.getWriter().getEmail())
                .build();
    }
}
