package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse extends BasicResponse{

    private final static String SUCCESS_STATUS = "OK";
    private String status;
    public SuccessResponse() {
        this.status = SUCCESS_STATUS;
    }
}
