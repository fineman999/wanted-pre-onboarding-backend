package wanted.community.user.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.user.presentation.request.UserCreateRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("회원가입")
    void create() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email("test@gmail.com")
                .password("123456789")
                .build();

        // when & then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreateRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.email").value(userCreateRequest.getEmail()));
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 형식이 아닌 경우")
    void create_fail_email() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email("test")
                .password("123456789")
                .build();

        // when & then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreateRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.message").value("이메일 형식이 올바르지 않습니다."))
                .andExpect(jsonPath("$.error.status").value(400));
    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호가 8자리 미만인 경우")
    void create_fail_password() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email("test@naver.com")
                .password("1234567")
                .build();

        // when & then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreateRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.message").value("패스워드는 8글자 이상이어야 합니다."))
                .andExpect(jsonPath("$.error.status").value(400));
    }
}