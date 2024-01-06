package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.dto.ParkingLotDto;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.entity.Parking;
import softee5.demo.exception.NoExistException;
import softee5.demo.repository.ParkingRepository;

import java.text.NumberFormat;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ParkingRepository parkingRepository;

    private static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
    public HomeResponseDto homeInfo(String name) {
        Parking parking = parkingRepository.findByName(name)
                .orElseThrow(() -> new NoExistException("존재하지 않는 주차장입니다."));

        ParkingLotDto parkingLotDto = ParkingLotDto.getParkingLotDto(parking);

        /**
         * 주차요금 계산하는 로직 추가 예정
         */

        String price = numberFormat.format(1000) + "원";

        return HomeResponseDto.getHomeResponseDto(price, parkingLotDto);
    }

}
