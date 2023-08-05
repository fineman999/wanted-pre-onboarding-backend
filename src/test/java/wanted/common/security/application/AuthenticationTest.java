package wanted.common.security.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.CommunityApplication;
import wanted.community.user.application.AuthenticationServiceImpl;
import wanted.community.user.application.port.UserCreateDto;
import wanted.community.user.domain.User;
import wanted.community.user.presentation.port.UserService;
import wanted.community.user.presentation.request.LoginRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(classes = CommunityApplication.class)
class AuthenticationTest {

    @Autowired
    private AuthenticationServiceImpl authentication;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        User user = userService.create(UserCreateDto.builder()
                .email("test@gmail.com")
                .password("123456789")
                .build());


    }

    @Test
    @DisplayName("사용자 이메일과 비밀번호로 인증 토큰을 생성하는 테스트")
    void authenticate() {

        AuthenticationServiceImpl testAuthentication = new AuthenticationServiceImpl(
                authenticationManager,
                new FakeJwtGenerator("test")
        );

        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("123456789")
                .build();

        String token = testAuthentication.authenticate(loginRequest);


        assertThat(token).isEqualTo("test");

    }

    @Test
    @DisplayName("사용자 이메일 잘못 입력시 인증 토큰 생성 실패 테스트")
    void authenticate_fail_email() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test")
                .password("123456789")
                .build();

        assertThatThrownBy(
                () -> authentication.authenticate(loginRequest)
        )
        .isInstanceOf(InternalAuthenticationServiceException.class)
        .hasMessage("해당 이메일을 가진 유저가 없습니다.");

    }

    @Test
    @DisplayName("사용자 비밀번호 잘못 입력시 인증 토큰 생성 실패 테스트")
    void authenticate_fail_password() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("12345678")
                .build();

        Assertions.assertThatThrownBy(
                () -> authentication.authenticate(loginRequest)
        ).isInstanceOf(BadCredentialsException.class);
    }
}