package wanted.community.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    @DisplayName("패스워드 생성 테스트")
    void create() {
        Password password = Password.create("testing...");

        assertThat(password).isEqualTo(Password.create("testing..."));
    }
}