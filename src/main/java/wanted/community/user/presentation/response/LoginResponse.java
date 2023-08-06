package wanted.community.user.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginResponse {

    @Schema(description = "JWT 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0Z")
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public static LoginResponse from(String token) {
        return new LoginResponse(token);
    }
}
