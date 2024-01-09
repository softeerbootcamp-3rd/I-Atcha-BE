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
    private int count;
    private String totalTime;
    private String totalFee;
    private String avgTime;
    private String avgFee;
    List<HistoryDto> historys;

    public static HistoryListResponseDto getHistoryListResponseDto(int count, String totalTime, String totalFee,
            String avgTime, String avgFee, List<HistoryDto> historyDtos){
        return HistoryListResponseDto.builder()
                .count(count)
                .totalTime(totalTime)
                .totalFee(totalFee)
                .avgTime(avgTime)
                .avgFee(avgFee)
                .historys(historyDtos)
                .build();
    }
}
