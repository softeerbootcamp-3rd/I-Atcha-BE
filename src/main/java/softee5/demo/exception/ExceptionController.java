package softee5.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity< ? extends BasicResponse> noContentExHandle(NoContentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

}
