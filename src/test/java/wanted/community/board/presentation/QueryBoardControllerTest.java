package wanted.community.board.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.CommunityApplication;
import wanted.community.board.domain.Board;
import wanted.community.board.infrastructure.entity.BoardEntity;
import wanted.community.board.infrastructure.port.BoardJpaRepository;
import wanted.community.user.application.port.UserRepository;
import wanted.community.user.domain.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(classes = CommunityApplication.class)
@Transactional
class QueryBoardControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${sql.script.delete.board}")
    private String deleteBoardSql;

    @Value("${sql.script.delete.users}")
    private String deleteUserSql;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardJpaRepository boardJpaRepository;

    @AfterEach
    void afterEach() {
        jdbc.update(deleteBoardSql);
        jdbc.update(deleteUserSql);
    }

    @SqlGroup({
            @Sql(scripts = "/insertUserTest.sql"), @Sql("/insertBoardTest.sql")
    })
    @DisplayName("게시글 목록을 조회한다.")
    @Test
    void getPage() throws Exception {

        final String page = "0";
        final String size = "2";

        mockMvc.perform(get("/api/v1/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", page)
                .param("size", size))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.totalPages").value(6))
                .andExpect(jsonPath("$.response.size").value(2))
                .andExpect(jsonPath("$.response.content[0].id").value(1))
                .andExpect(jsonPath("$.response.content[1].id").value(2));
    }

    @SqlGroup({
            @Sql(scripts = "/insertUserTest.sql"), @Sql("/insertBoardTest.sql")
    })
    @DisplayName("게시글 목록을 조회한다. - page와 size값이 없는 경우 기본값")
    @Test
    void getPageWithoutPage() throws Exception {

        mockMvc.perform(get("/api/v1/boards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.size").value(10))
                .andExpect(jsonPath("$.response.number").value(0));
    }

    @DisplayName("게시글을 상세 조회한다.")
    @Sql(scripts = "/insertUserTest.sql")
    @Test
    void getById() throws Exception {
        User user = userRepository.getByEmail("admin@localhost");
        Board board = Board.builder()
                .title("title")
                .content("content")
                .writer(user)
                .build();
        BoardEntity boardEntity = boardJpaRepository.save(BoardEntity.from(board));
        String id = String.valueOf(boardEntity.getId());


        mockMvc.perform(get("/api/v1/boards/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.id").value(1))
                .andExpect(jsonPath("$.response.title").value(board.getTitle()))
                .andExpect(jsonPath("$.response.content").value(board.getContent()))
                .andExpect(jsonPath("$.response.writerEmail").value(user.getEmail()))
                .andExpect(jsonPath("$.response.createdAt").exists())
                .andExpect(jsonPath("$.response.updatedAt").exists());
    }


}