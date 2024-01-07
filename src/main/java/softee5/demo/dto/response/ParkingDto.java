package softee5.demo.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingDto {
    private List<ParkingInfo> parkingList = new ArrayList<>();

    public void add(String parkingName) {
        parkingList.add(new ParkingInfo(parkingName));
    }

    public void add(String parkingName, Double distance) {
        parkingList.add(new ParkingInfo(parkingName, distance));
    }
}

@Data
class ParkingInfo {
    String name;
    Double distance;

    public ParkingInfo(String name) {
        this.name = name;
    }

    public ParkingInfo(String name, Double distance) {
        this.name = name;
        this.distance = Math.round(distance * 10) / 10.0;
    }
}