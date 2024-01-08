package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse{
    private final static String ERROR_STATUS = "ERROR";

    private String status;
    private String message;

    public ErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse error(String message){
        return new ErrorResponse(ERROR_STATUS, message);
    }

}
