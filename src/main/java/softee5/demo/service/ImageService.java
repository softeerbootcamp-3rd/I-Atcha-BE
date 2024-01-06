package softee5.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softee5.demo.entity.Image;
import softee5.demo.repository.ImageRepository;
import softee5.demo.utils.S3Uploader;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public List<Image> uploadImage(List<MultipartFile> multipartFiles) throws IOException {
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String link = s3Uploader.upload(multipartFile);
            LocalTime now = LocalTime.now();
            Image image = Image.builder()
                    .parkingTime(now)
                    .link(link)
                    .build();
            imageRepository.save(image);
            images.add(image);
        }
        return images;
    }
}
