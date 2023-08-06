package wanted.community.board.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateRequest {

    @Schema(description = "제목", example = "제목입니다. 한 글자 이상 입력하세요.")
    @NotBlank(message = "제목을 입력해주세요.")
    private final String title;

    @Schema(description = "내용", example = "내용입니다. 한 글자 이상 입력하세요.")
    @NotBlank(message = "내용을 입력해주세요.")
    private final String content;

    @Builder
    public BoardCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
