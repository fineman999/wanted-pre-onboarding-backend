package wanted.community.board.domain;


import lombok.Builder;
import lombok.Getter;
import wanted.community.board.presentation.request.BoardCreateRequest;

@Getter
public class BoardCreateDto {
    private final String title;
    private final String content;

    @Builder
    public BoardCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static BoardCreateDto of(BoardCreateRequest boardCreateRequest) {
        return BoardCreateDto.builder()
                .title(boardCreateRequest.getTitle())
                .content(boardCreateRequest.getContent())
                .build();
    }
}
