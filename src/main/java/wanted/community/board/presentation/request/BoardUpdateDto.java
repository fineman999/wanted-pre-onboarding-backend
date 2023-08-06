package wanted.community.board.presentation.request;

import lombok.Builder;
import lombok.Getter;

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
