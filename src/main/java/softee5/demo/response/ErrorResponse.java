package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends BasicResponse{
    private final static String ERROR_STATUS = "ERROR";
    private String message;

    public ErrorResponse(String message) {
        super(ERROR_STATUS);
        this.message = message;
    }
}
