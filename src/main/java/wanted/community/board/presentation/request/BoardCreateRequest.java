package wanted.community.board.presentation.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateRequest {
    private final String title;
    private final String content;

    @Builder
    public BoardCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
