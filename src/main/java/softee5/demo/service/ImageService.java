package softee5.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softee5.demo.entity.Image;
import softee5.demo.repository.ImageRepository;
import softee5.demo.utils.S3Uploader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    public void uploadImage(MultipartFile multipartFile) throws IOException {
        String link = s3Uploader.upload(multipartFile);
        LocalTime now = LocalTime.now();
        Image image = Image.builder()
                .parkingTime(now)
                .link(link)
                .build();
        imageRepository.save(image);
    }
}
