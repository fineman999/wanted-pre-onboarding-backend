package wanted.community.board.presentation.response;

import lombok.Builder;
import lombok.Getter;
import wanted.community.board.domain.Board;

@Getter
public class BoardResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String writerEmail;

    @Builder
    public BoardResponse(Long id, String title, String content, String writerEmail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerEmail = writerEmail;
    }

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writerEmail(board.getWriter().getEmail())
                .build();
    }
}
