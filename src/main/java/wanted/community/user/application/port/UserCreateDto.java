package wanted.community.user.application.port;

import lombok.Builder;
import lombok.Getter;
import wanted.community.user.presentation.request.UserCreateRequest;

@Getter
public class UserCreateDto {
    private final String email;
    private final String password;

    @Builder
    public UserCreateDto(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public static UserCreateDto of(UserCreateRequest userCreateRequest) {
        return UserCreateDto.builder()
                .email(userCreateRequest.getEmail())
                .password(userCreateRequest.getPassword())
                .build();
    }
}
