package softee5.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.response.SuccessResponse;
import softee5.demo.service.LocationService;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/set/{memberId}")
    public ResponseEntity<? extends BasicResponse> setLocation(@PathVariable("memberId") String memberId, @RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude) {
        locationService.setLocation(memberId, latitude, longitude);
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @GetMapping("/isFar/{memberId}")
    public ResponseEntity<? extends BasicResponse> findParking(@PathVariable("memberId") String memberId, @RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude){
        Boolean result = locationService.checkFar(memberId, latitude, longitude);

        return ResponseEntity.ok().body(new DataResponse<>(result));
    }
}
