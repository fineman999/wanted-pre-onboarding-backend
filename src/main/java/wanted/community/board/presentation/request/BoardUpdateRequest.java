package wanted.community.board.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardUpdateRequest {

    @Schema(description = "게시글 번호", example = "1")
    private final Long id;

    @Schema(description = "제목", example = "제목입니다.")
    private final String title;

    @Schema(description = "내용", example = "내용입니다.")
    private final String content;

    @Builder
    public BoardUpdateRequest(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
