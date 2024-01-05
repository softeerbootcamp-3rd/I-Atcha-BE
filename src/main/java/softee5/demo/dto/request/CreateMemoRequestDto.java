package softee5.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMemoRequestDto {
    @NotBlank(message = "메모 내용은 필수입니다.")
    private String content;


}
