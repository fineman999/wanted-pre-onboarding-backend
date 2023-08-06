package wanted.community.board.application.port;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardPageDto {

    private final int page;

    private final int size;

    @Builder
    public BoardPageDto(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public static BoardPageDto of(int page, int size) {
        return BoardPageDto.builder()
                .page(page)
                .size(size)
                .build();
    }
}
