package wanted.community.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import wanted.community.user.domain.Email;
import wanted.community.user.domain.Password;
import wanted.community.user.domain.Role;
import wanted.community.user.domain.User;

@Getter
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail();
        userEntity.password = user.getPassword();
        userEntity.role = user.getRole();
        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .email(Email.of(email))
                .password(Password.of(password))
                .role(role)
                .build();
    }
}
