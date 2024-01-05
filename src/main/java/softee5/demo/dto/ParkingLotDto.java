package softee5.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import softee5.demo.entity.Parking;

@Data
@Builder
@AllArgsConstructor
public class ParkingLotDto {
    private String name;
    private String fee;
    private String runningTime;
    private String discount;

    public static ParkingLotDto getParkingLotDto(Parking parking){
        return ParkingLotDto.builder()
                .name(parking.getName())
                .fee(parking.getFee().replace(",", " |"))
                .runningTime(parking.getRunningTime().replace("," ," |"))
                .discount(parking.getDiscount().replace(",", "\n"))
                .build();
    }

}
