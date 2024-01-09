package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.dto.request.HomeExitRequestDto;
import softee5.demo.dto.response.HistoryDetailResponseDto;
import softee5.demo.dto.response.HistoryListResponseDto;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.response.DataResponse;
import softee5.demo.response.SingleResponse;
import softee5.demo.service.HistoryService;
import softee5.demo.service.HomeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {
    private final HomeService homeService;
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity< ? > homeInfo(@RequestParam(value = "name") String name){
        HomeResponseDto homeResponseDto =  homeService.homeInfo(name);

        return ResponseEntity.ok().body(DataResponse.success(homeResponseDto));
    }

    @PostMapping("/exit")
    public ResponseEntity< ? > exit(@RequestBody @Valid HomeExitRequestDto homeExitRequestDto){
        homeService.exit(homeExitRequestDto);

        return ResponseEntity.ok().body(SingleResponse.success());
    }

    @GetMapping("/history/{member_id}") //history 목록
    public ResponseEntity< ? > historyList(@PathVariable("member_id") Long memberId){
        HistoryListResponseDto  historyListResponseDto = historyService.historyList(memberId);

        return ResponseEntity.ok().body(DataResponse.success(historyListResponseDto));
    }

    @GetMapping("/history/detail/{history_id}")
    public ResponseEntity<? > historyDetail(@PathVariable("history_id") Long historyId){
        HistoryDetailResponseDto historyDetailResponseDto = historyService.historyDetail(historyId);

        return ResponseEntity.ok().body(DataResponse.success(historyDetailResponseDto));
    }

    @DeleteMapping("/history/{history_id}")
    public ResponseEntity<?> historyDelete(@PathVariable("history_id") Long historyId){
        historyService.historyDelete(historyId);

        return ResponseEntity.ok().body(SingleResponse.success());
    }
}
