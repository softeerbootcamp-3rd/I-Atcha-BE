package softee5.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softee5.demo.dto.HistoryDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryListResponseDto {
    List<HistoryDto> historys;

    public static HistoryListResponseDto getHistoryListResponseDto(List<HistoryDto> historyDtos){
        return HistoryListResponseDto.builder()
                .historys(historyDtos)
                .build();
    }
}
