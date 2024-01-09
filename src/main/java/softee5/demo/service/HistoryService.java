package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softee5.demo.dto.HistoryDto;
import softee5.demo.dto.response.HistoryDetailResponseDto;
import softee5.demo.dto.response.HistoryListResponseDto;
import softee5.demo.entity.History;
import softee5.demo.entity.Image;
import softee5.demo.exception.NoExistException;
import softee5.demo.repository.HistoryRepository;
import softee5.demo.repository.ImageRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yy.MM.dd");
    private static final int FREE = 0;
    private static final Long MEMBER_ID = 1L;

    private final HistoryRepository historyRepository;
    private final ImageRepository imageRepository;

    public HistoryListResponseDto historyList(Long memberId) {
        List<HistoryDto> historyDtos = new ArrayList<>();

        List<History> historys = historyRepository.findByMemberId(MEMBER_ID);

        int count = historys.size();
        LocalTime totalParkingTime = LocalTime.of(0, 0);
        int totalFee = 0;
        int totalMinutes = 0;

        for (History history : historys) {
            LocalTime parkingTime = extractTime(history.getParkingTime()); //문자열 시간을 LocalTime으로 가져오기

            //총 주차장 이용 시간 구하기
            totalParkingTime = totalParkingTime.plusHours(parkingTime.getHour()).plusMinutes(parkingTime.getMinute());
            totalMinutes += parkingTime.getHour() * 60 + parkingTime.getMinute();

            //총 지불 금액 구하기
            history.getPaidFee();
            totalFee += parseCurrency(history.getPaidFee());

            //주차 날짜 구하기
            LocalDateTime createTime = history.getCreateTime();
            String parkingDate = formatDate(createTime);

            HistoryDto historyDto = HistoryDto.getHistoryDto(history.getHistoryID(), parkingDate, history.getPaidFee(),
                    history.getParkingTime(), history.getParking().getName());

            historyDtos.add(historyDto);
        }

        //시간
        String totalTimeToString = totalParkingTime.format(DateTimeFormatter.ofPattern("H시간 mm분")); //총 이용시간
        String avgTime = getAvgTime(totalMinutes, count); //평균 이용시간

        //금액
        String totalFeeToString = totalFee + "원";//총 지불 금액
        String avgFee = calculateAverage(totalFee, count) + "원";//평균 지불 금액

        System.out.println("totalTimeToString = " + totalTimeToString);
        System.out.println("totalFeeToString = " + totalFeeToString);
        System.out.println("avgTimeToString = " + avgTime);
        System.out.println("avgFee = " + avgFee);

        return HistoryListResponseDto.getHistoryListResponseDto(historyDtos);
    }

    private String getAvgTime(int totalMinutes, int count) {
        // 평균 분 계산
        int averageMinutes = totalMinutes / count;

        // 평균 분을 LocalTime 으로 변환하여 반환
        int averageHours = averageMinutes / 60;
        int remainingMinutes = averageMinutes % 60;

        String avgTime = LocalTime.of(averageHours, remainingMinutes).format(DateTimeFormatter.ofPattern("H시간 mm분"));
        return avgTime;
    }

    private long calculateAverage(int sum, int count) { //평균 계산
        return count > 0 ? Math.round((double) sum / count) : 0;
    }
    private int parseCurrency(String currency) {
        return Integer.parseInt(currency.replaceAll("[^0-9]", ""));//숫자 제외 나머지 제거
    }

    private LocalTime extractTime(String parkingTime) {
        String[] parts = parkingTime.split(" ");
        int hours = Integer.parseInt(parts[0].replace("시간", "")); //시간 추출
        int minutes = Integer.parseInt(parts[1].replace("분", "")); //분 추출

        return LocalTime.of(hours, minutes);
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

    private String findFeeInfo(int freeTime, int minuteRate, int addFee) {
        StringBuilder feeInfo = new StringBuilder();

        if(freeTime != FREE ){
            feeInfo .append("초기무료 ").append(freeTime).append("분").append("|");
        }

        feeInfo .append("추가요금 ").append(minuteRate).append("분당 ").append(addFee).append("원");

        return feeInfo .toString();
    }

}
