package wanted.community.user.presentation.response;

import lombok.Getter;
import wanted.community.user.domain.User;

@Getter
public class UserResponse {
    private final Long id;
    private final String email;


    public UserResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail());
    }
}
