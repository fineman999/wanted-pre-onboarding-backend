package wanted.community.user.domain;

import lombok.EqualsAndHashCode;
import wanted.community.user.application.port.PasswordEncoderHolder;

@EqualsAndHashCode
public class User {

    private final Email email;
    private final Password password;

    public User(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public static User create(String email, String password, PasswordEncoderHolder passwordEncoderHolder) {
        return new User(Email.create(email), Password.create(password, passwordEncoderHolder));
    }
}
