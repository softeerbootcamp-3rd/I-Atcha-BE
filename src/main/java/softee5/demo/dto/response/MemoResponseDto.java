package softee5.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoResponseDto {
    private Long memoId;

    public static MemoResponseDto getMemoResponseDto(Long memoId) {
        return MemoResponseDto.builder()
                .memoId(memoId)
                .build();
    }
}
