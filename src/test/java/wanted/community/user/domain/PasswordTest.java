package wanted.community.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wanted.community.user.application.port.PasswordEncoderHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordTest {

    private PasswordEncoderHolder passwordEncoder;
    @BeforeEach
    void setUp() {
        passwordEncoder = password -> "어쨌든 암호화임!";
    }

    @Test
    @DisplayName("패스워드 생성 테스트")
    void create() {
        Password password = Password.create("testing...", passwordEncoder);

        assertThat(password).isEqualTo(Password.create("testing...", passwordEncoder));
    }

    @Test
    @DisplayName("패스워드가 8글자 이상이면 생성 완료")
    void checkLength() {
        Password password = Password.create("12345678", passwordEncoder);

        assertThat(password).isNotNull();
    }

    @Test
    @DisplayName("패스워드가 8글자 미만이면 예외 발생")
    void checkLengthException() {
        assertThatThrownBy(() -> Password.create("1234567", passwordEncoder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 8자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("패스워드는 암호화되어 저장되어야 한다.")
    void passwordEncryption() {
        Password password = Password.create("12345678", passwordEncoder);

        assertThat(password).isEqualTo(Password.create("어쨌든 암호화임!", passwordEncoder));
    }
}