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
    public ResponseEntity< ? > findParking(@RequestBody @Valid UserDto userDto){
        Boolean result = locationService.checkFar(userDto.getId(), userDto.getLatitude(), userDto.getLongitude());

        return ResponseEntity.ok().body(DataResponse.success(result));
    }
}
