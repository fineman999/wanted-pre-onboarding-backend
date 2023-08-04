package wanted.community.user.presentation.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    private final String email;
    private final String password;

    @Builder
    public UserCreateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }



}
