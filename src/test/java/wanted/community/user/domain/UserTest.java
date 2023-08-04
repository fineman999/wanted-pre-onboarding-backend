package wanted.community.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wanted.community.user.application.port.PasswordEncoderHolder;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private PasswordEncoderHolder passwordEncoderHolder;
    @BeforeEach
    void setUp() {
        passwordEncoderHolder = password -> "encodedPassword";
    }

    @Test
    @DisplayName("유저를 생성합니다.")
    void create() {
        String email = "test@gmail.com";
        String password = "123456789";

        User user = User.create(email, password, passwordEncoderHolder);

        assertThat(user).isEqualTo(User.create(email, password, passwordEncoderHolder));
    }
}