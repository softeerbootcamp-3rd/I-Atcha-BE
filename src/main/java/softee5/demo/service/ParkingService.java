package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.dto.response.ParkingDto;
import softee5.demo.entity.Parking;
import softee5.demo.repository.ParkingRepository;
import softee5.demo.utils.Compute;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingDto getParking(Double latitude, Double longitude) {
        List<Parking> parkingList = parkingRepository.findAll();
        ParkingDto parkingDto = new ParkingDto();

        parkingList.sort((p1, p2) -> {
            double distance1 = Compute.haversine(latitude, longitude, p1.getLatitude(), p1.getLongitude());
            double distance2 = Compute.haversine(latitude, longitude, p2.getLatitude(), p2.getLongitude());

            return Double.compare(distance1, distance2);
        });

        for (Parking parking : parkingList) {
            double distance = Compute.haversine(latitude, longitude, parking.getLatitude(), parking.getLongitude());
            parkingDto.add(parking.getName(), distance);
        }

        return parkingDto;
    }

    public ParkingDto getParking() {
        List<Parking> parkingList = parkingRepository.findAll();
        ParkingDto parkingDto = new ParkingDto();

        for (Parking parking : parkingList) {
            parkingDto.add(parking.getName());
        }

        return parkingDto;
    }
}