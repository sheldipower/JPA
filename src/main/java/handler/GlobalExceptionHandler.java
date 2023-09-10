package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = RuntimeException.class)
    public void handleException(Exception exception, HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", exception.getMessage());
                put("type", exception.getClass());
            }
        }));
        log.error(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.badRequest().body(
                methodArgumentNotValidException.getFieldErrors().stream()
                        .map(fieldError -> Map.of(
                                fieldError.getField(),
                                String.format("%s. актуальное значение: %s",
                                        fieldError.getDefaultMessage(),
                                        fieldError.getRejectedValue())
                        ))
                        .collect(Collectors.toList())
        );
    }
}