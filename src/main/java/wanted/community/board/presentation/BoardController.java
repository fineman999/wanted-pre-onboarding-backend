package wanted.community.board.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.common.utils.ApiUtils.ApiResult;
import wanted.community.board.domain.Board;
import wanted.community.board.application.port.BoardCreateDto;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.board.presentation.request.BoardCreateRequest;
import wanted.community.board.application.port.BoardUpdateDto;
import wanted.community.board.presentation.request.BoardUpdateRequest;
import wanted.community.board.presentation.response.BoardResponse;
import wanted.community.user.presentation.AuthenticationService;

import static wanted.common.utils.ApiUtils.success;

@Tag(name = "게시판 관리 API", description = "게시판을 관리하는 API 입니다. 게시판을 생성, 수정, 삭제할 수 있습니다. 게시판을 생성할 때는 로그인이 필요합니다.")
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final AuthenticationService authenticationService;

    @Operation(summary = "게시판 생성", description = "게시판을 생성합니다. 인증이 필요합니다.")
    @PostMapping
    public ResponseEntity<ApiResult<BoardResponse>> save(
            @Valid @RequestBody BoardCreateRequest boardCreateRequest
    ) {
        String email = authenticationService.getEmail();
        Board board = boardService.save(BoardCreateDto.of(boardCreateRequest), email);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success(BoardResponse.from(board)));
    }

    @Operation(summary = "게시판 수정", description = "게시판을 수정합니다. 인증이 필요합니다.")
    @PutMapping
    public ResponseEntity<ApiResult<BoardResponse>> update(
            @Valid @RequestBody BoardUpdateRequest boardUpdateRequest
    ) {
        Board board = boardService.updateById(boardUpdateRequest.getId(), BoardUpdateDto.of(boardUpdateRequest));
        return ResponseEntity.ok(success(BoardResponse.from(board)));
    }


    @Operation(summary = "게시판 삭제", description = "게시판을 삭제합니다. 인증이 필요합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<Void>> delete(
            @PathVariable Long id
    ) {
        boardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }


}
