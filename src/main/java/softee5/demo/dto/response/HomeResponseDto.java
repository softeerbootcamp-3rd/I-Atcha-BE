package softee5.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softee5.demo.dto.ParkingLotDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponseDto {

    private final static String NONE = "없음";

    private String myParkingFee;
    private String link;
    private String content;
    private ParkingLotDto parkingLotDto;

    public static HomeResponseDto getHomeResponseDto(String myParkingFee, String link, String content, ParkingLotDto parkingLotDto){
        return HomeResponseDto.builder()
                .myParkingFee(myParkingFee)
                .link(link == "" ? NONE : link)
                .content(content == "" ? NONE : content)
                .parkingLotDto(parkingLotDto)
                .build();
    }

}
