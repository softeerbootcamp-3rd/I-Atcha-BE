package softee5.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softee5.demo.dto.request.UserDto;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.service.LocationService;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/isFar")
    public ResponseEntity<? extends BasicResponse> findParking(@RequestBody @Valid UserDto userDto){
        Boolean result = locationService.checkFar(userDto.getId(), userDto.getLatitude(), userDto.getLongitude());

        return ResponseEntity.ok().body(new DataResponse<>(result));
    }
}
