package softee5.demo.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import softee5.demo.dto.response.ImageLinkResponseDto;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BasicResponse> saveImage(@RequestPart("file") List<MultipartFile> files) throws IOException {
        List<Image> images = imageService.uploadImage(files);
        return ResponseEntity.ok().body(new DataResponse(ImageSaveResponseDto.build(images)));
    }

    @GetMapping("/image/{image_id}")
    public ResponseEntity<? extends BasicResponse> showImage(@PathVariable("image_id") long id) {
        Image image = imageService.getImage(id);
        return ResponseEntity.ok().body(new DataResponse<>(ImageLinkResponseDto.build(image)));
    }

    @PutMapping(value = "/save/{image_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<? extends BasicResponse> changeImage(@PathVariable("image_id") long id,
                                                               @RequestPart("file") MultipartFile multipartFile) throws IOException {
        Image image = imageService.changeImage(id, multipartFile);
        return ResponseEntity.ok().body(new DataResponse<>(ImageSaveResponseDto.build(image)));
    }
}
