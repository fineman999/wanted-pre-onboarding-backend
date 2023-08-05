package wanted.community.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.community.user.application.port.UserCreateDto;
import wanted.community.user.domain.User;
import wanted.community.user.presentation.port.UserService;
import wanted.community.user.presentation.request.LoginRequest;
import wanted.community.user.presentation.request.UserCreateRequest;
import wanted.community.user.presentation.response.LoginResponse;
import wanted.community.user.presentation.response.UserResponse;
import wanted.common.utils.ApiUtils.ApiResult;

import static wanted.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<ApiResult<UserResponse>> create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.create(UserCreateDto.of(userCreateRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success(UserResponse.from(user)));
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호를 입력하세요")
    @PostMapping("/login")
    public ResponseEntity<ApiResult<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {
        String token = authenticationService.authenticate(request);
        return ResponseEntity.ok(success(LoginResponse.from(token)));
    }
}
