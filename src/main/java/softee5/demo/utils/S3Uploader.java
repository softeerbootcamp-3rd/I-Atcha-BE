package softee5.demo.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softee5.demo.repository.ImageRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * S3파일 업로드
     */
    public String upload(MultipartFile multipartFile) throws IOException {
        File file = convertFile(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("File convert Error")); // 파일 변환
        String imageUrl = uploadS3(multipartFile, file);    // S3 업로드
        file.delete();  // 로컬 파일 삭제
        return imageUrl;
    }

    /**
     * S3파일 삭제
     */
    public void delete(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String uploadS3(MultipartFile multipartFile, File file) {
        String fileName = multipartFile.getName();
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private Optional<File> convertFile(MultipartFile multipartFile) throws IOException {
        File converFile = new File(multipartFile.getOriginalFilename());
        if (converFile.createNewFile()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(converFile)) {
                fileOutputStream.write(multipartFile.getBytes());
            }
        }
        return Optional.of(converFile);
    }

}
