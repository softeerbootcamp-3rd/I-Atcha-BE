package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataResponse<T> extends BasicResponse{

    private String status;
    private T data;

    public DataResponse(T data) {
        this.status = HttpStatus.OK.name();
        this.data = data;
    }



}
