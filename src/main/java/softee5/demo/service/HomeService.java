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
import java.time.Duration;
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
    private final ImageRepository imageRepository;
    private final HistoryRepository historyRepository;
    private final FeeRepository feeRepository;
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.KOREA);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yy.MM.dd");
    private static final DateTimeFormatter START_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final String MONEY_UNIT = "원";
    private static final int NONE_IMAGE = 0;
    private static final Long MEMBER_ID = 1L;
    private static final int FREE = 0;

    public HomeResponseDto homeInfo(String name) {
        Parking parking = parkingRepository.findByName(name)
                .orElseThrow(() -> new NoExistException("존재하지 않는 주차장입니다."));

        long feeId = parking.getFee().getFeeId();
        Fee fee = feeRepository.findById(feeId).get();

        //주차장 요금 정보
        int freeTime = fee.getFreeTime();
        int minuteRate = fee.getMinuteRate();
        int addFee = fee.getAddFee();

        ParkingLot parkingLot = ParkingLot.getParkingLotDto(findFeeInfo(freeTime, minuteRate, addFee), parking);

        //요금 계산
        Member member = memberRepository.findById(MEMBER_ID).get();
        int price = getPrice(freeTime, minuteRate, addFee);
        LocalDateTime startTime = member.getUpdateTime();
        return HomeResponseDto.getHomeResponseDto(startTime.format(START_TIME_FORMATTER),NUMBER_FORMAT.format(price) + MONEY_UNIT, parkingLot);
    }

    public void exit(HomeExitRequestDto homeExitRequestDto) {
        History history;

        String name = homeExitRequestDto.getName();
        Parking parking = parkingRepository.findByName(name).orElseThrow(() -> new NoExistException("존재하지 않는 주차장입니다."));

        Member member = memberRepository.findById(MEMBER_ID).get();

        history = History.createHistory(member, homeExitRequestDto.getContent(),parking,
                homeExitRequestDto.getPaidFee(), homeExitRequestDto.getParkingTime());

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

        int freeTime = history.getParking().getFee().getFreeTime();
        int minuteRate = history.getParking().getFee().getMinuteRate();
        int addFee = history.getParking().getFee().getAddFee();

        List<String> link = imageRepository.findLinkByHistoryId(historyId);

        return HistoryDetailResponseDto.getHistoryDetailResponseDto(history, findFeeInfo(freeTime, minuteRate, addFee), link);
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

    private String formatDate(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }

    private static String findFeeInfo(int freeTime, int minuteRate, int addFee) {
        StringBuilder feeInfo = new StringBuilder();

        if(freeTime != FREE ){
            feeInfo .append("초기무료 ").append(freeTime).append("분").append("|");
        }

        feeInfo .append("추가요금 ").append(minuteRate).append("분당 ").append(addFee).append("원");

        return feeInfo .toString();
    }

    private int getPrice(int freeTime, int minuteRate, int addFee) {
        Member member = memberRepository.findById(MEMBER_ID).get();

        Duration duration = Duration.between(member.getUpdateTime(), LocalDateTime.now());
        int minutes = (int) duration.toMinutes();

        int overTime = Math.max(0, minutes - freeTime);
        int count = (overTime + minuteRate - 1) / minuteRate;

        return addFee*count;
    }

}
