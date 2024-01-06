package softee5.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softee5.demo.dto.ParkingLot;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponseDto {

    private String myParkingFee;
    private ParkingLot parkingLot;

    public static HomeResponseDto getHomeResponseDto(String myParkingFee, ParkingLot parkingLot){
        return HomeResponseDto.builder()
                .myParkingFee(myParkingFee)
                .parkingLot(parkingLot)
                .build();
    }

}
