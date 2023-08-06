package wanted.community.board.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.common.utils.ApiUtils;
import wanted.common.utils.ApiUtils.ApiResult;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.board.presentation.request.BoardPageRequest;
import wanted.community.board.presentation.response.BoardPageResponse;
import wanted.community.board.presentation.response.BoardResponse;

import static wanted.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class QueryBoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<ApiResult<Page<BoardPageResponse>>> findAll(
            @ModelAttribute BoardPageRequest boardPageRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(success(boardService.findAll(boardPageRequest)
                .map(BoardPageResponse::from)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<BoardResponse>> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(success(BoardResponse.from(boardService.getById(id))));
    }


}
