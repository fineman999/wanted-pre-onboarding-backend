package wanted.community.user.application.port;

import lombok.Builder;
import lombok.Getter;

@Getter
public record UserCreateDto(String email, String password) {
    @Builder
    public UserCreateDto {
    }


}
