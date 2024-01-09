package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SingleResponse{

    private String status;

    public SingleResponse(String status) {
        this.status = status;
    }

    public static SingleResponse success(){
        return new SingleResponse(HttpStatus.OK.name());
    }

}
