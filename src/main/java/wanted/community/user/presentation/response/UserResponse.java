package wanted.community.user.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import wanted.community.user.domain.User;

@Getter
public class UserResponse {

    @Schema(description = "유저 ID", example = "1")
    private final Long id;

    @Schema(description = "유저 이메일", example = "test@naver.com")
    private final String email;


    public UserResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail());
    }
}
