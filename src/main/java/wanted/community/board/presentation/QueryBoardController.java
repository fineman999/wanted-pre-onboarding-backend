package wanted.community.board.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.common.utils.ApiUtils.ApiResult;
import wanted.community.board.presentation.port.BoardService;
import wanted.community.board.application.port.BoardPageDto;
import wanted.community.board.presentation.response.BoardPageResponse;
import wanted.community.board.presentation.response.BoardResponse;

import static wanted.common.utils.ApiUtils.success;

@Tag(name = "게시판 API", description = "게시판 조회 API입니다.")
@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class QueryBoardController {
    private final BoardService boardService;

    @Operation(summary = "게시판 목록 조회", description = "게시판 목록을 조회합니다. 페이지네이션을 지원합니다. 기본값: page=0, size=10")
    @GetMapping
    public ResponseEntity<ApiResult<Page<BoardPageResponse>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(success(boardService.findAll(BoardPageDto.of(page, size))
                        .map(BoardPageResponse::from)));
    }

    @Operation(summary = "게시판 상세 조회", description = "게시판 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<BoardResponse>> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(success(BoardResponse.from(boardService.getById(id))));
    }


}
