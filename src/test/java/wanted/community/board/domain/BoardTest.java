package wanted.community.board.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.user.application.port.PasswordEncoderHolder;
import wanted.community.user.domain.Email;
import wanted.community.user.domain.Password;
import wanted.community.user.domain.Role;
import wanted.community.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private PasswordEncoderHolder passwordEncoderHolder;

    private User writer;
    @BeforeEach
    void setUp() {
        passwordEncoderHolder = password -> "encodedPassword";
        writer = User.builder()
                .id(1L)
                .email(Email.create("test@gmail.com"))
                .password(Password.create("password", passwordEncoderHolder))
                .role(Role.USER)
                .build();
    }

    @Test
    @DisplayName("게시판 생성")
    void create() {

        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title("title")
                .content("content")
                .build();



        Board board = Board.create(boardCreateDto, writer);

        assertThat(board).isEqualTo(Board.builder()
                .title("title")
                .content("content")
                .writer(writer)
                .build());
    }

    @Test
    @DisplayName("게시판 생성 실패 - 제목이 비어있음")
    void create_fail_title_empty() {

        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title("")
                .content("content")
                .build();

        Assertions.assertThatThrownBy(
                () -> Board.create(boardCreateDto, writer)
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

        Assertions.assertThatThrownBy(
                () -> Board.create(boardCreateDto, writer)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("내용이 비어있습니다.");
    }
}
