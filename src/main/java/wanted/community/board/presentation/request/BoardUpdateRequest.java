package wanted.community.board.presentation.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardUpdateRequest {

    private final Long id;
    private final String title;
    private final String content;

    @Builder
    public BoardUpdateRequest(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
