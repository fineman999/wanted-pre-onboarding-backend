package wanted.community.user.domain;

import wanted.community.user.application.port.PasswordEncoderHolder;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(value, password1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
