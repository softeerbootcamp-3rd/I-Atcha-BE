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

    private String myParkingFee;
    private ParkingLotDto parkingLotDto;

    public static HomeResponseDto getHomeResponseDto(String myParkingFee, ParkingLotDto parkingLotDto){
        return HomeResponseDto.builder()
                .myParkingFee(myParkingFee)
                .parkingLotDto(parkingLotDto)
                .build();
    }

}
