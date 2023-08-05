package wanted.community.board.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateDto {
    private final String title;
    private final String content;

    @Builder
    public BoardCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
