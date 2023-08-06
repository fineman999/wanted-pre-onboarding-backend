package wanted.community.board.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.board.domain.Board;
import wanted.community.board.application.port.BoardUpdateDto;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.Email;
import wanted.community.user.domain.Password;
import wanted.community.user.domain.Status;
import wanted.community.user.domain.User;
import wanted.community.user.mock.FakeUserRepository;

import static org.assertj.core.api.Assertions.assertThat;

class BoardServiceTest {

    private BoardServiceImpl boardService;
    private User writer;

    @BeforeEach
    void setUp() {
        FakeBoardRepository fakeBoardRepository = new FakeBoardRepository();
        UserRepository userRepository= new FakeUserRepository();
        writer = User.builder()
                .id(1L)
                .email(Email.of("test@test.com"))
                .password(Password.of("123456789"))
                .status(Status.USER)
                .build();
        userRepository.save(writer);
        Board board = Board.builder()
                .id(3L)
                .title("test")
                .content("test")
                .writer(writer)
                .build();
        fakeBoardRepository.save(board);
        boardService = new BoardServiceImpl(fakeBoardRepository, userRepository);
    }

    @Test
    @DisplayName("게시판 생성")
    void save() {
        BoardCreateDto boardCreateDto = BoardCreateDto.builder()
                .title("test")
                .content("test")
                .build();

        Board board = boardService.save(boardCreateDto, "test@test.com");

        assertThat(board).isEqualTo(Board.builder()
                .id(1L)
                .title("test")
                .content("test")
                .writer(writer)
                .build());

    }

    @Test
    @DisplayName("게시판 수정")
    void update() {

        String email = "test@test.com";

        BoardUpdateDto boardUpdateDto = BoardUpdateDto.builder()
                .title("update")
                .content("update")
                .build();

        Board updatedBoard = boardService.updateById(3L, boardUpdateDto, email);

        assertThat(updatedBoard).isEqualTo(Board.builder()
                .id(3L)
                .title("update")
                .content("update")
                .writer(writer)
                .build());
    }

    @Test
    @DisplayName("게시판 삭제")
    void delete() {
        String email = "test@test.com";

        boardService.deleteById(3L, email);

        Assertions.assertThatThrownBy(
                () -> boardService.getById(3L)
        ).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("해당하는 게시글이 없습니다.");
    }
}