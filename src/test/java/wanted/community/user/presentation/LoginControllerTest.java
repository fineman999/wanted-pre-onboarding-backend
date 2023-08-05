package wanted.community.user.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.user.application.port.UserCreateDto;
import wanted.community.user.presentation.port.UserService;
import wanted.community.user.presentation.request.LoginRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class LoginControllerTest {


    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        userService.save(UserCreateDto.builder()
                .email("test@gmail.com")
                .password("123456789")
                .build());
    }

    @Test
    @DisplayName("사용자 이메일과 비밀번호로 인증 토큰을 생성하는 테스트")
    void login() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("123456789")
                .build();

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.token").exists());
    }

    @Test
    @DisplayName("사용자 이메일과 비밀번호로 입력했을 때 사용자가 없는 경우")
    void login_fail() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@test.com")
                .password("123456789")
                .build();
        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.message").value("해당 이메일을 가진 유저가 없습니다."));
    }

    @Test
    @DisplayName("사용자 이메일과 비밀번호로 입력했을 때 비밀번호가 틀린 경우")
    void login_fail_password() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("1234569123")
                .build();
        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error.message").value("이메일 혹은 비밀번호가 일치하지 않습니다. 다시 확인해주세요."));
    }
}