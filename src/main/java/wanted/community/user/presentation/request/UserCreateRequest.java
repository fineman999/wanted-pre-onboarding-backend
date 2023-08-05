package wanted.community.user.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequest {


    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;


    @Pattern(regexp = ".{8,}", message = "패스워드는 8글자 이상이어야 합니다.")
    private final String password;

    @Builder
    public UserCreateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
