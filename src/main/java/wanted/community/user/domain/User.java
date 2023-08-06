package wanted.community.user.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import wanted.community.user.application.port.PasswordEncoderHolder;

@ToString
@EqualsAndHashCode
public class User {

    private final Long id;
    private final Email email;
    private final Password password;
    private final Status status;

    @Builder
    public User(Long id, Email email, Password password, Status status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public static User create(String email, String password, PasswordEncoderHolder passwordEncoderHolder) {
        return User.builder()
                .email(Email.create(email))
                .password(Password.create(password, passwordEncoderHolder))
                .status(Status.USER)
                .build();
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public boolean compare(User writer) {
        return this.id.equals(writer.getId());
    }
}
