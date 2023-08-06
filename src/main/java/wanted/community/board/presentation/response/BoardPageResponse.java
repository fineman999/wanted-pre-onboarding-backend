package wanted.community.board.presentation.response;

import lombok.Builder;
import lombok.Getter;
import wanted.community.board.domain.Board;


@Getter
public class BoardPageResponse {
    private final Long id;
    private final String title;
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
