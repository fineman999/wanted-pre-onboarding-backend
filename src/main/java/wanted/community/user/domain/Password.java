package wanted.community.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import wanted.community.user.application.port.PasswordEncoderHolder;

@Getter
@ToString
@EqualsAndHashCode
public class Password {

    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password create(String password, PasswordEncoderHolder passwordEncoder) {
        checkLength(password);
        return new Password(passwordEncoder.encode(password));
    }

    private static void checkLength(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
    }

    public static Password of(String password) {
        return new Password(password);
    }
}
