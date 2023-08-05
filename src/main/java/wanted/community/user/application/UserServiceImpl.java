package wanted.community.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.user.application.port.PasswordEncoderHolder;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;
import wanted.community.user.presentation.port.UserService;
import wanted.community.user.application.port.UserCreateDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderHolder passwordEncoderHolder;


    @Transactional
    @Override
    public User create(UserCreateDto userCreateDto) {
        User user = User.create(userCreateDto.getEmail(), userCreateDto.getPassword(), passwordEncoderHolder);
        user = userRepository.create(user);
        return user;
    }
}
