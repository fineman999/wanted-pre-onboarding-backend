package wanted.community.board.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.CommunityApplication;
import wanted.community.board.presentation.request.BoardCreateRequest;
import wanted.community.user.application.port.JwtGenerator;
import wanted.community.user.application.port.UserCreateDto;
import wanted.community.user.domain.User;
import wanted.community.user.presentation.port.UserService;
import wanted.security.domain.CustomUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = CommunityApplication.class)
@Transactional
class BoardControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    private String token;

    @BeforeEach
    void setUp() {

        User user = userService.save(UserCreateDto.builder()
                .email("test@gmail.com")
                .password("123456789")
                .build());

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                "123456789"
        ));
        CustomUserDetails UserToken = (CustomUserDetails) authenticate.getPrincipal();
        token = jwtGenerator.generateToken(UserToken);
    }

    @Test
    @DisplayName("사용자 토큰을 사용해서 게시글을 생성하는 테스트")
    void login() throws Exception {
        BoardCreateRequest board = BoardCreateRequest.builder()
                .title("textTitle")
                .content("testContent")
                .build();

        mockMvc.perform(post("/api/v1/boards")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(board)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.title").value("textTitle"))
                .andExpect(jsonPath("$.response.content").value("testContent"))
                .andExpect(jsonPath("$.response.writerEmail").value("test@gmail.com"));
    }
}