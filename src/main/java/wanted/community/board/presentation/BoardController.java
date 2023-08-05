package wanted.community.board.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wanted.common.utils.ApiUtils;
import wanted.common.utils.ApiUtils.ApiResult;
import wanted.community.board.domain.Board;
import wanted.community.board.domain.BoardCreateDto;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.board.presentation.request.BoardCreateRequest;
import wanted.community.board.presentation.response.BoardResponse;
import wanted.community.user.presentation.AuthenticationService;

import java.util.List;

import static wanted.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<ApiResult<BoardResponse>> save(
            @Valid @RequestBody BoardCreateRequest boardCreateRequest
    ) {
        String email = authenticationService.getEmail();
        log.info("email: {}", email);
        Board board = boardService.save(BoardCreateDto.of(boardCreateRequest), email);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success(BoardResponse.from(board)));
    }

}
