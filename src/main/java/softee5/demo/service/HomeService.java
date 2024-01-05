package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.dto.ParkingLotDto;
import softee5.demo.dto.request.HomeRequestDto;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.entity.Parking;
import softee5.demo.exception.NoExistException;
import softee5.demo.repository.ImageRepository;
import softee5.demo.repository.MemoRepository;
import softee5.demo.repository.ParkingRepository;

import java.text.NumberFormat;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemoRepository memoRepository;
    private final ImageRepository imageRepository;
    private final ParkingRepository parkingRepository;

    private static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
    public HomeResponseDto homeInfo(HomeRequestDto homeRequestDto) {
        String content = "";
        String link = "";

        if(homeRequestDto.getImageId() != 0){
            link = imageRepository.findById(homeRequestDto.getImageId())
                    .orElseThrow(() -> new NoExistException("존재하지 않는 이미지입니다.")).getLink();
        }

        if(homeRequestDto.getMemoId() != 0){
            content = memoRepository.findById(homeRequestDto.getMemoId())
                    .orElseThrow(() -> new NoExistException("존재하지 않는 메모입니다.")).getContent();
        }

        String parkingLotName = homeRequestDto.getParkingLotName();
        Parking parking = parkingRepository.findByName(parkingLotName)
                .orElseThrow(() -> new NoExistException("존재하지 않는 주차장입니다."));

        ParkingLotDto parkingLotDto = ParkingLotDto.getParkingLotDto(parking);

        /**
         * 주차요금 계산하는 로직 추가 예정
         */
        String price = numberFormat.format(1000) + "원";

        return HomeResponseDto.getHomeResponseDto(price, link, content, parkingLotDto);
    }

}
