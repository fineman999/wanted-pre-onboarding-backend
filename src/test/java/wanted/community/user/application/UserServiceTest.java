package wanted.community.user.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wanted.community.user.application.port.PasswordEncoderHolder;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.Email;
import wanted.community.user.domain.Password;
import wanted.community.user.domain.User;
import wanted.community.user.mock.FakeUserRepository;
import wanted.community.user.application.port.UserCreateDto;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private UserServiceImpl userServiceImpl;
    private PasswordEncoderHolder passwordEncoderHolder;
    @BeforeEach
    void setUp() {
        UserRepository userRepository= new FakeUserRepository();
        passwordEncoderHolder = password -> "암튼인코딩임!";
        userServiceImpl = new UserServiceImpl(userRepository, passwordEncoderHolder);
    }

    @Test
    @DisplayName("유저를 생성합니다.")
    void create() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("test@test.com")
                .password("123456789")
                .build();

        User user = userServiceImpl.create(userCreateDto);

        assertThat(user).isEqualTo(User.builder()
                .id(1L)
                .email(Email.create("test@test.com"))
                .password(Password.create("123456789", passwordEncoderHolder))
                .build());
    }
}