package softee5.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.SuccessResponse;
import softee5.demo.service.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/camera")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BasicResponse> saveImage(MultipartFile multipartFile) throws IOException {
        imageService.uploadImage(multipartFile);
        return ResponseEntity.ok().body(new SuccessResponse());
    }
}
