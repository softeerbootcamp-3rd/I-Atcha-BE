package softee5.demo.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SuccessResponse extends BasicResponse{

    private String status;
    public SuccessResponse() {
        this.status = HttpStatus.OK.name();
    }

}
