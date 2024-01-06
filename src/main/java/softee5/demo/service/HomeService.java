package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.dto.ParkingLotDto;
import softee5.demo.dto.request.HomeExitRequestDto;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.entity.*;
import softee5.demo.exception.NoExistException;
import softee5.demo.repository.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final static Long MEMBER_ID = 1L;

    private final ParkingRepository parkingRepository;
    private final MemberRepository memberRepository;
    private final MemoRepository memoRepository;
    private final ImageRepository imageRepository;
    private final HistoryRepository historyRepository;

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

    public void exit(HomeExitRequestDto homeExitRequestDto) {
        Memo saveMemo = null;
        History history;

        String name = homeExitRequestDto.getName();
        Parking parking = parkingRepository.findByName(name).orElseThrow(() -> new NoExistException("존재하지 않는 주차장입니다."));

        Member member = memberRepository.findById(MEMBER_ID).get();

        if(homeExitRequestDto.getContent() != "없음"){
            Memo memo = Memo.createMemo(homeExitRequestDto.getContent());
            saveMemo = memoRepository.save(memo);
        }

        history = History.createHistory(member, saveMemo, parking, homeExitRequestDto.getPaidFee(), homeExitRequestDto.getParkingTime());

        History saveHistory = historyRepository.save(history);

        if(homeExitRequestDto.getImageId() != 0){
            Image image = imageRepository.findById(homeExitRequestDto.getImageId()).orElseThrow(() -> new NoExistException("존재하지 않는 이미지입니다."));
            image.setHistory(saveHistory);
        }
    }

}
