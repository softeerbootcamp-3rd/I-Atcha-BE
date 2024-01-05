package softee5.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softee5.demo.service.ImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/camera")
public class ImageController {
    private final ImageService imageService;
}
