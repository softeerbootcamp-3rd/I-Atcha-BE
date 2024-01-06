package softee5.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import softee5.demo.dto.response.ImageSaveResponseDto;
import softee5.demo.entity.Image;
import softee5.demo.response.BasicResponse;
import softee5.demo.response.DataResponse;
import softee5.demo.response.SuccessResponse;
import softee5.demo.service.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/camera")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BasicResponse> saveImage(@RequestPart("file") List<MultipartFile> files) throws IOException {
        List<Image> images = imageService.uploadImage(files);
        return ResponseEntity.ok().body(new DataResponse(ImageSaveResponseDto.build(images)));
    }
}
