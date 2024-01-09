package softee5.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserParkStartDto {
    @JsonFormat(pattern = "HH:mm")
    private LocalDateTime parkingStartTime;
}
