package softee5.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import softee5.demo.entity.Parking;

@Data
@Builder
@AllArgsConstructor
public class ParkingLot {
    private String name;
    private String fee;
    private String runningTime;
    private String discount;

    public static ParkingLot getParkingLotDto(String fee,Parking parking){
        return ParkingLot.builder()
                .name(parking.getName())
                .fee(fee)
                .runningTime(parking.getRunningTime().replace("," ," |"))
                .discount(parking.getDiscount().replace(",", "\n"))
                .build();
    }

}
