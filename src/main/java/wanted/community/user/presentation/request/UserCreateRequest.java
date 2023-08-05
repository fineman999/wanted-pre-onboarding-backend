package wanted.community.user.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotNull(message = "email cannot be null")
    @Email
    private final String email;


    @Pattern(regexp = ".{8,}", message = "패스워드는 8글자 이상이어야 합니다.")
    private final String password;

    public UserCreateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
