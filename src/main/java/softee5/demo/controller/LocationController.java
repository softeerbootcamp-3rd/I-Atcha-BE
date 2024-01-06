package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softee5.demo.dto.request.UserDto;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.response.SuccessResponse;
import softee5.demo.service.LocationService;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/set")
    public ResponseEntity<? extends BasicResponse> setLocation(@RequestBody @Valid UserDto userDto) {
        locationService.setLocation(userDto.getId(), userDto.getLatitude(), userDto.getLongitude());
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @GetMapping("/isFar")
    public ResponseEntity<? extends BasicResponse> findParking(@RequestBody @Valid UserDto userDto){
        Boolean result = locationService.checkFar(userDto.getId(), userDto.getLatitude(), userDto.getLongitude());

        return ResponseEntity.ok().body(new DataResponse<>(result));
    }
}
