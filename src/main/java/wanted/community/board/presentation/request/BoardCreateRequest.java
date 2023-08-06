package wanted.community.board.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private final String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private final String content;

    @Builder
    public BoardCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
