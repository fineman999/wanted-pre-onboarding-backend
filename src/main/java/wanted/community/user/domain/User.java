package wanted.community.user.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import wanted.community.user.application.port.PasswordEncoderHolder;

@Getter
@ToString
@EqualsAndHashCode
public class User {

    private final Long id;
    private final Email email;
    private final Password password;

    @Builder
    public User(Long id, Email email, Password password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static User create(String email, String password, PasswordEncoderHolder passwordEncoderHolder) {
        return User.builder()
                .email(Email.create(email))
                .password(Password.create(password, passwordEncoderHolder))
                .build();
    }

}
