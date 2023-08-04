package wanted.community.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {

    @Test
    @DisplayName("이메일 생성 테스트")
    void create() {
        Email email = Email.create("test@gmail.com");

        assertThat(email).isEqualTo(Email.create("test@gmail.com"));
    }

    @Test
    @DisplayName("이메일 형식이 아니면 예외 발생")
    void checkEmailFormat() {
        assertThatThrownBy(() -> Email.create("testgmail.com"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이메일 형식이 올바르지 않습니다.");
    }




}