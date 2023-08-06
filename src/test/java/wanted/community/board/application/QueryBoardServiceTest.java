package wanted.community.board.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;
import wanted.community.board.domain.Board;
import wanted.community.board.infrastructure.port.BoardJpaRepository;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.board.application.port.BoardPageDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
public class QueryBoardServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${sql.script.delete.board}")
    private String deleteBoardSql;

    @Value("${sql.script.delete.users}")
    private String deleteUserSql;

    @Autowired
    private BoardService boardService;

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
    @ParameterizedTest
    @CsvSource({"3", "2"})
    @DisplayName("게시글 목록을 조회한다.")
    void getPage(int size) {
        BoardPageDto boardPageDto = BoardPageDto.builder()
                .page(0)
                .size(size)
                .build();

        Page<Board> boards = boardService.findAll(boardPageDto);

        assertAll(
                () -> assertThat(boards.getSize()).isEqualTo(size)
        );

    }

    @SqlGroup({
            @Sql(scripts = "/insertUserTest.sql", config = @SqlConfig(commentPrefix = "`")),
            @Sql(scripts = "/insertBoardTest.sql",config = @SqlConfig(commentPrefix = "`"))
    })
    @Test
    @DisplayName("게시글을 상세 조회한다.")
    void getById() {


        Board board = boardService.getById(1L);

        assertAll(
                () -> assertThat(board.getTitle()).isEqualTo("title1"),
                () -> assertThat(board.getContent()).isEqualTo("content1"),
                () -> assertThat(board.getWriter().getId()).isEqualTo(1L)
        );
    }
}
