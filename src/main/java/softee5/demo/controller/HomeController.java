package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.dto.request.HomeExitRequestDto;
import softee5.demo.dto.response.HistoryListResponseDto;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.response.SuccessResponse;
import softee5.demo.service.HomeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    @GetMapping
    public ResponseEntity< ? extends BasicResponse> homeInfo(@RequestParam(value = "name") String name){
        HomeResponseDto homeResponseDto =  homeService.homeInfo(name);

        return ResponseEntity.ok().body(new DataResponse(homeResponseDto));
    }

    @PostMapping("/exit")
    public ResponseEntity< ? extends BasicResponse> exit(@RequestBody @Valid HomeExitRequestDto homeExitRequestDto){
        homeService.exit(homeExitRequestDto);

        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @GetMapping("/history/{member_id}")
    public ResponseEntity< ? extends  BasicResponse> historyList(@PathVariable("member_id") Long memberId){
        HistoryListResponseDto  historyListResponseDto = homeService.historyList(memberId);

        return ResponseEntity.ok().body(new DataResponse(historyListResponseDto));
    }
}
