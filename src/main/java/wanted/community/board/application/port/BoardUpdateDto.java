package wanted.community.board.application.port;

import lombok.Builder;
import lombok.Getter;
import wanted.community.board.presentation.request.BoardUpdateRequest;

@Getter
public class BoardUpdateDto {

    private final String title;

    private final String content;

    @Builder
    public BoardUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static BoardUpdateDto of(BoardUpdateRequest boardUpdateRequest) {
        return BoardUpdateDto.builder()
                .title(boardUpdateRequest.getTitle())
                .content(boardUpdateRequest.getContent())
                .build();
    }
}
