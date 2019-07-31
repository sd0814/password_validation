package practice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(0)
@Slf4j
public class CommonExceptionHandler {
    @Value("${application.debug}")
    private boolean debugMode;

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", "server error");
        resultMap.put("timestamp", System.currentTimeMillis());

        if (debugMode) {
            resultMap.put("message", ExceptionUtils.getMessage(exception));
            resultMap.put("stacktrace", ExceptionUtils.getStackTrace(exception));
        }

        if (exception instanceof NoHandlerFoundException) {
            return new ResponseEntity<>(resultMap, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
