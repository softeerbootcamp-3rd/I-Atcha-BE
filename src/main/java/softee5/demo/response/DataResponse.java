package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponse<T> extends BasicResponse{
    private final static String SUCCESS_STATUS = "OK";

    private String status;
    private T data;

    public DataResponse(T data) {
        this.status = SUCCESS_STATUS;
        this.data = data;
    }

}
