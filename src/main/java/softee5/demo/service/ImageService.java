package softee5.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softee5.demo.entity.Image;
import softee5.demo.exception.ErrorMessage;
import softee5.demo.exception.NoContentException;
import softee5.demo.exception.NoExistException;
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
        if (multipartFiles.isEmpty()) {
            throw new NoContentException("사진이 없습니다.");
        }

        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String saveFileName = s3Uploader.upload(multipartFile);
            String link = s3Uploader.getFileUrl(saveFileName);
            createImage(link, images);
        }
        return images;
    }

    private void createImage(String link, List<Image> images) {
        LocalTime now = LocalTime.now();
        Image image = Image.builder()
                .parkingTime(now)
                .link(link)
                .build();
        imageRepository.save(image);
        images.add(image);
    }

    public Image getImage(long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new NoExistException(ErrorMessage.NOT_EXIST_PARKING));
    }

    @Transactional
    public Image changeImage(Long imageId, MultipartFile multipartFile) throws IOException {
        Image existingImage = imageRepository.findById(imageId).orElseThrow(() -> new NoExistException(ErrorMessage.NOT_EXIST_IMAGE));
        s3Uploader.delete(existingImage.getLink());
        String link = s3Uploader.upload(multipartFile);
        existingImage.changeLink(link);
        imageRepository.save(existingImage);
        return existingImage;
    }
}
