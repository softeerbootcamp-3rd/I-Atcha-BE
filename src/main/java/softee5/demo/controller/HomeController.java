package softee5.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.dto.response.HomeResponseDto;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
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
}
