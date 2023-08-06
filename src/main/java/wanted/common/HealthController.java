package wanted.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Tag(name = "헬스 체크")
    @Operation(summary = "헬스 체킹 API")
    @GetMapping("/health_check")
    public String health() {
        return "ok";
    }

}
