package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.dto.request.UserLocationDto;
import softee5.demo.dto.response.ParkingDto;
import softee5.demo.response.DataResponse;
import softee5.demo.service.ParkingService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/parking")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParkingController {
    private final ParkingService parkingService;

    @GetMapping("/withLocation")
    public ResponseEntity< ? > findParking(@RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude){
        ParkingDto parkingList = parkingService.getParking(latitude, longitude);

        return ResponseEntity.ok().body(DataResponse.success(parkingList));
    }

    @GetMapping("/withoutLocation")
    public ResponseEntity< ? > findParkingWithoutLocation(){
        ParkingDto parkingList = parkingService.getParking();

        return ResponseEntity.ok().body(DataResponse.success(parkingList));
    }
}
