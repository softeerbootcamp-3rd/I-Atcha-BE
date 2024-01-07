package softee5.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softee5.demo.dto.ParkingLot;
import softee5.demo.entity.History;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDetailResponseDto {

    private String parkingDate;
    private String parkingTime;
    private String paidFee;
    private List<String> link;
    private ParkingLot parkingLot;
    private String content;

    public static HistoryDetailResponseDto getHistoryDetailResponseDto(History history, String fee, List<String> link){
        return HistoryDetailResponseDto.builder()
                .parkingDate(formatDate(history.getCreateTime()))
                .parkingTime(history.getParkingTime())
                .paidFee(history.getPaidFee())
                .link(link)
                .parkingLot(ParkingLot.getParkingLotDto(fee,history.getParking()))
                .content(history.getMemo())
                .build();
    }

    private static String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yy.MM.dd");
        return localDateTime.format(DATE_FORMATTER);
    }
}
