package wanted.community.board.presentation.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardPageRequest {
    private final int page;
    private final int size;

    @Builder
    public BoardPageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
