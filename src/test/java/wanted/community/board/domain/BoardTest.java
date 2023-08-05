package wanted.community.board.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {


    @Test
    @DisplayName("게시판 생성")
    void create() {

        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title("title")
                .content("content")
                .build();
        Long userId = 1L;


        Board board = Board.create(boardCreateDto, userId);

        assertThat(board).isEqualTo(Board.builder()
                .title("title")
                .content("content")
                .userId(1L)
                .build());
    }

    @Test
    @DisplayName("게시판 생성 실패 - 제목이 비어있음")
    void create_fail_title_empty() {

        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title("")
                .content("content")
                .build();
        Long userId = 1L;

        Assertions.assertThatThrownBy(
                () -> Board.create(boardCreateDto, userId)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제목이 비어있습니다.");
    }

    @Test
    @DisplayName("게시판 생성 실패 - 내용이 비어있음")
    void create_fail_content_empty() {

        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title("title")
                .content("")
                .build();
        Long userId = 1L;

        Assertions.assertThatThrownBy(
                () -> Board.create(boardCreateDto, userId)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("내용이 비어있습니다.");
    }
}
