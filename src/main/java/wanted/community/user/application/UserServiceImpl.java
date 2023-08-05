package wanted.community.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.user.application.port.PasswordEncoderHolder;
import wanted.community.user.application.port.UserCreateDto;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;
import wanted.community.user.presentation.port.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderHolder passwordEncoderHolder;


    @Transactional
    @Override
    public User save(UserCreateDto userCreateDto) {
        return (User) userRepository.findByEmail(userCreateDto.getEmail())
                .map(user -> {
                    throw new DuplicateKeyException("이미 가입되어 있는 유저입니다.");
                })
                .orElseGet(() -> create(userCreateDto));
    }

    private User create(UserCreateDto userCreateDto) {
        User user = User.create(userCreateDto.getEmail(), userCreateDto.getPassword(), passwordEncoderHolder);
        user = userRepository.save(user);
        return user;
    }
}
