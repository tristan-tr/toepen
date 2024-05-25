package tristtr.toepen.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tristtr.toepen.exceptions.ErrorInfo;
import tristtr.toepen.exceptions.GameAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> exceptionStatusMap = new HashMap<>();

    static {
        exceptionStatusMap.put(IllegalArgumentException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(GameAlreadyExistsException.class, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({IllegalArgumentException.class, GameAlreadyExistsException.class})
    @ResponseBody
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest req, Exception e) {
        HttpStatus status = exceptionStatusMap.getOrDefault(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new ErrorInfo(req.getRequestURL().toString(), e.getMessage()), status);
    }
}
