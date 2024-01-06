package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softee5.demo.dto.request.HomeRequestDto;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.service.HomeService;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping("/home")
    public ResponseEntity< ? extends BasicResponse> homeInfo(@RequestBody @Valid HomeRequestDto homeRequestDto){
        HomeResponseDto homeResponseDto =  homeService.homeInfo(homeRequestDto);

        return ResponseEntity.ok().body(new DataResponse(homeResponseDto));
    }
}
