package wanted.community.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wanted.community.user.application.port.PasswordEncoderHolder;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;
import wanted.community.user.presentation.port.UserService;
import wanted.community.user.presentation.request.UserCreateRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderHolder passwordEncoderHolder;


    @Override
    public User create(UserCreateRequest userCreateRequest) {
        User user = User.create(userCreateRequest.getEmail(), userCreateRequest.getPassword(), passwordEncoderHolder);
        user = userRepository.create(user);
        return user;
    }
}
