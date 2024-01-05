package softee5.demo.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingDto {
    private List<String> parkingList = new ArrayList<>();

    public void add(String parkingName) {
        parkingList.add(parkingName);
    }
}
