package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.dto.HistoryDto;
import softee5.demo.dto.ParkingLot;
import softee5.demo.dto.request.HomeExitRequestDto;
import softee5.demo.dto.response.HistoryDetailResponseDto;
import softee5.demo.dto.response.HistoryListResponseDto;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.entity.*;
import softee5.demo.exception.NoExistException;
import softee5.demo.repository.*;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ParkingRepository parkingRepository;
    private final MemberRepository memberRepository;
    private final MemoRepository memoRepository;
    private final ImageRepository imageRepository;
    private final HistoryRepository historyRepository;

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.KOREA);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yy.MM.dd");
    private static final String MONEY_UNIT = "원";
    private static final String NONE_MENO = "없음";
    private static final int NONE_IMAGE = 0;
    private static final Long MEMBER_ID = 1L;

    public HomeResponseDto homeInfo(String name) {
        Parking parking = parkingRepository.findByName(name)
                .orElseThrow(() -> new NoExistException("존재하지 않는 주차장입니다."));

        ParkingLot parkingLot = ParkingLot.getParkingLotDto(parking);

        /**
         * 주차요금 계산하는 로직 추가 예정
         */

        String price = NUMBER_FORMAT.format(1000) + MONEY_UNIT;

        return HomeResponseDto.getHomeResponseDto(price, parkingLot);
    }

    public void exit(HomeExitRequestDto homeExitRequestDto) {
        Memo saveMemo = null;
        History history;

        String name = homeExitRequestDto.getName();
        Parking parking = parkingRepository.findByName(name).orElseThrow(() -> new NoExistException("존재하지 않는 주차장입니다."));

        Member member = memberRepository.findById(MEMBER_ID).get();

        if(homeExitRequestDto.getContent() != NONE_MENO){
            Memo memo = Memo.createMemo(homeExitRequestDto.getContent());
            saveMemo = memoRepository.save(memo);
        }

        history = History.createHistory(member, saveMemo, parking, homeExitRequestDto.getPaidFee(), homeExitRequestDto.getParkingTime());

        History saveHistory = historyRepository.save(history);

        if(homeExitRequestDto.getImageId() != NONE_IMAGE){
            Image image = imageRepository.findById(homeExitRequestDto.getImageId()).orElseThrow(() -> new NoExistException("존재하지 않는 이미지입니다."));
            image.setHistory(saveHistory);
        }
    }

    public HistoryListResponseDto historyList(Long memberId) {
        List<HistoryDto> historyDtos = new ArrayList<>();

        List<History> historys = historyRepository.findByMemberId(memberId);

        for (History history : historys) {
            LocalDateTime createTime = history.getCreateTime();
            String parkingDate = formatDate(createTime);

            HistoryDto historyDto = HistoryDto.getHistoryDto(history.getHistoryID(), parkingDate, history.getPaidFee(),
                    history.getParkingTime(), history.getParking().getName());

            historyDtos.add(historyDto);
        }

        return HistoryListResponseDto.getHistoryListResponseDto(historyDtos);
    }

    public HistoryDetailResponseDto historyDetail(Long historyId) {
        History history = historyRepository.findById(historyId).orElseThrow(() -> new NoExistException("존재하지 않는 historyId 입니다."));

        List<String> link = imageRepository.findLinkByHistoryId(historyId);

        return HistoryDetailResponseDto.getHistoryDetailResponseDto(history, link);
    }

    private String formatDate(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }


    public void historyDelete(Long historyId) {
        if(!imageRepository.findImageByHistoryId(historyId).isEmpty()){//이미지 먼저 삭제
            List<Image> images = imageRepository.findImageByHistoryId(historyId);
            imageRepository.deleteAll(images);
        }

        //기록 삭제
        History history = historyRepository.findById(historyId).orElseThrow(() -> new NoExistException("존재하지 않는 이용 기록입니다."));

        historyRepository.delete(history);
    }
}
