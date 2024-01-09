package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.dto.request.UserDto;
import softee5.demo.response.DataResponse;
import softee5.demo.response.SingleResponse;
import softee5.demo.service.LocationService;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/set")
    public ResponseEntity< ? > setLocation(@RequestBody @Valid UserDto userDto) {
        locationService.setLocation(userDto.getId(), userDto.getLatitude(), userDto.getLongitude());
        return ResponseEntity.ok().body(SingleResponse.success());
    }

    @GetMapping("/isFar")
    public ResponseEntity< ? > findParking(@RequestBody @Valid UserDto userDto) {
        Boolean result = locationService.checkFar(userDto.getId(), userDto.getLatitude(), userDto.getLongitude());
        return ResponseEntity.ok().body(DataResponse.success(result));
    }

    @PostMapping("/set/{memberId}")
    public ResponseEntity<?> setLocation(@PathVariable("memberId") Long memberId, @RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude) {
        locationService.setLocation(memberId, latitude, longitude);
        return ResponseEntity.ok().body(SingleResponse.success());
    }

    @GetMapping("/isFar/{memberId}")
    public ResponseEntity<?> findParking(@PathVariable("memberId") Long memberId, @RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude){
        Boolean result = locationService.checkFar(memberId, latitude, longitude);

        return ResponseEntity.ok().body(DataResponse.success(result));
    }
}
