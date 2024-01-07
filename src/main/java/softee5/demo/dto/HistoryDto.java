package softee5.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {
    private Long historyId;
    private String parkingDate;
    private String paidFee;
    private String parkingTime;
    private String parkingName;

    public static HistoryDto getHistoryDto(Long historyId, String parkingDate, String paidFee, String parkingTime, String parkingName) {
        return HistoryDto.builder()
                .historyId(historyId)
                .parkingDate(parkingDate)
                .paidFee(paidFee)
                .parkingTime(parkingTime)
                .parkingName(parkingName)
                .build();
    }
}
