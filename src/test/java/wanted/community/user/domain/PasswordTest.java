package wanted.community.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    @DisplayName("패스워드 생성 테스트")
    void create() {
        Password password = Password.create("testing...");

        assertThat(password).isEqualTo(Password.create("testing..."));
    }

    @Test
    @DisplayName("패스워드가 8글자 이상이면 생성 완료")
    void checkLength() {
        Password password = Password.create("12345678");

        assertThat(password).isNotNull();
    }

    @Test
    @DisplayName("패스워드가 8글자 미만이면 예외 발생")
    void checkLengthException() {
        assertThatThrownBy(() -> Password.create("1234567"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 8자 이상이어야 합니다.");
    }
}