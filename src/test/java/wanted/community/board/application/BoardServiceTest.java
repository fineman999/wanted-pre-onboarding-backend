package wanted.community.board.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wanted.community.board.domain.Board;
import wanted.community.board.domain.BoardCreateDto;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.Email;
import wanted.community.user.domain.Password;
import wanted.community.user.domain.Role;
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
                .role(Role.USER)
                .build();
        userRepository.save(writer);
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
}