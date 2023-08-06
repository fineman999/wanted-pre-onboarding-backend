package wanted.community.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequest {

    @Schema(description = "이메일",  example = "test@naver.com")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;


    @Schema(description = "비밀번호",  example = "1234!Aa1234")
    @Pattern(regexp = ".{8,}", message = "패스워드는 8글자 이상이어야 합니다.")
    private final String password;

    @Builder
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
