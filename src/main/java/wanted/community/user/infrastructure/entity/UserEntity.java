package wanted.community.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import wanted.community.user.domain.Email;
import wanted.community.user.domain.Password;
import wanted.community.user.domain.Status;
import wanted.community.user.domain.User;

@Getter
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail();
        userEntity.password = user.getPassword();
        userEntity.status = user.getStatus();
        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .email(Email.of(email))
                .password(Password.of(password))
                .status(status)
                .build();
    }
}
