package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataResponse<T>{

    private String status;
    private T data;

    public DataResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> DataResponse<T> success(T data){
        return new DataResponse<>(HttpStatus.OK.name(), data);
    }
}
