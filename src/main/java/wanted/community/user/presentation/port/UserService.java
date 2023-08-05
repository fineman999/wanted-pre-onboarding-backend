package wanted.community.user.presentation.port;

import wanted.community.user.domain.User;
import wanted.community.user.application.port.UserCreateDto;

public interface UserService {
    User save(UserCreateDto userCreateDto);
}
