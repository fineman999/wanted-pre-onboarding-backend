package wanted.community.board.presentation;

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
import wanted.community.board.presentation.request.BoardUpdateDto;
import wanted.community.board.presentation.request.BoardUpdateRequest;
import wanted.community.board.presentation.response.BoardResponse;
import wanted.community.user.presentation.AuthenticationService;

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
        Board board = boardService.save(BoardCreateDto.of(boardCreateRequest), email);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success(BoardResponse.from(board)));
    }

    @PutMapping
    public ResponseEntity<ApiResult<BoardResponse>> update(
            @Valid @RequestBody BoardUpdateRequest boardUpdateRequest
    ) {
        Board board = boardService.updateById(boardUpdateRequest.getId(), BoardUpdateDto.of(boardUpdateRequest));
        return ResponseEntity.ok(success(BoardResponse.from(board)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<Void>> delete(
            @PathVariable Long id
    ) {
        boardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success(null));
    }


}
