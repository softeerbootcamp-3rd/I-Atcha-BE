package softee5.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HomeExitRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String paidFee;
    @NotNull
    private String parkingTime;
    private Long imageId;
    private String content;
}
