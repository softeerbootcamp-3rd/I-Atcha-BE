package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softee5.demo.dto.request.UserLocationDto;
import softee5.demo.dto.response.ParkingDto;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.service.ParkingService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingService parkingService;

    @GetMapping("/withLocation")
    public ResponseEntity<? extends BasicResponse> findParking(@RequestBody @Valid UserLocationDto userLocationDto){
        ParkingDto parkingList = parkingService.getParking(userLocationDto.getLatitude(), userLocationDto.getLongitude());

        return ResponseEntity.ok().body(new DataResponse<>(parkingList));
    }

    @GetMapping("/withoutLocation")
    public ResponseEntity<? extends BasicResponse> findParkingWithoutLocation(){
        ParkingDto parkingList = parkingService.getParking();

        return ResponseEntity.ok().body(new DataResponse<>(parkingList));
    }
}
